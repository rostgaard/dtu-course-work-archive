#include <intrinsics.h>
#include <stdio.h>
#include "board.h"
#include "sys.h"
#include "config.h"
#include "sdram_32M_16bit_drv.h"
#include "drv_glcd.h"
#include "cursor_arrow.h"
#include "includes.h"

#include "frequency.h"
#include "gpio.h"
#include "dac.h"
#include "adc.h"
#include "timer.h"
#include "zerocrossing.h"
#include "interrupt_handlers.h"
#include "filter.h"
#include "photo.h"
#include "button_lightgrey.h"
#include "Black_bg.h"
#include "touch_scr.h"




//float ADdata[2] = {0.0,0.0};    //([y(k-1) y(k)]) => ADdata(1)=y(k-1), ADdata(0)=y(k)
struct ADdata_t ADdata = {0.0,0.0};
struct ADC_p_p_t ADC_p_p;
extern struct time_t real_time;
extern struct Touch_data_t Touch_data;
extern Button_coords_t Up_left_Button;
extern Button_coords_t Up_right_Button;

unsigned long timetick =0;
Int32U Data=0;

int channel = 0;
int adcCLK = 0;

float alpha;
float frequency;
float f_out;
int tick_count = 0;
int ADfilter_enable = true;
int linear_inerpolaion = false;
int blink_timer = 0;
int dac_timer = 0;
Int32U X_Left =5;
Int32U X_Right =310;
Int32U Y_Up =75;
Int32U Y_Down =113;
Int32U Linespace = 35*2;
float AD_zerocrossing_delay[2] = {0.0,0.0};   //([y(k-1) y(k)]) just after the zero-crossing accured


void Program_Init ();
void LCD_Init();
void LCD_Main_Screen();
void P_P_value();
void LCD_Config_Screen();
int between(float input, float lower_limit, float upper_limit);

extern void ADC_Inter_Handler ();
extern void Timer0IntrHandler ();
extern float Frequency_with_interpolation();
extern float Frequency_without_interpolation();
extern void update_alpha();
extern float ADfilter(Int32U Data);
extern void TouchScrInit();


extern FontType_t Terminal_9_12_6;
extern FontType_t Terminal_18_24_12;
extern float frequency;
extern int blink_timer;

int screen_state = -1;
int screen_state_is_changing = 1;

#define BUF ((struct uip_eth_hdr *)&uip_buf[0])


int main(void)
{

  unsigned int i;
uip_ipaddr_t ipaddr;
struct timer periodic_timer, arp_timer;



  
  Program_Init();
  
 //Setup VIC to respond to VIC_TIMER0 and call Timer0IntrHandler()
  VIC_SetVectoredIRQ(Timer0IntrHandler,1,VIC_TIMER0);
  VIC_SetVectoredIRQ(ADC_Inter_Handler,0,VIC_AD0);

  VICINTENABLE |= 1UL << VIC_AD0;  
  VICINTENABLE |= 1UL << VIC_TIMER0;
 // Start Timer
//  adc_start();
 //Enable interrupts
  __enable_interrupt();
  update_alpha(); //sets the alpha value for the low-pass filter 
  
 
/** WEB server */  
    // Sys timer init 1/100 sec tick
  clock_init(2);

  timer_set(&periodic_timer, CLOCK_SECOND / 2);
  timer_set(&arp_timer, CLOCK_SECOND * 10);

  while(!tapdev_init());

  // uIP web server
  // Initialize the uIP TCP/IP stack.
  uip_init();

  uip_ipaddr(ipaddr, 192,168,0,100);
  uip_sethostaddr(ipaddr);
  uip_ipaddr(ipaddr, 192,168,0,1);
  uip_setdraddr(ipaddr);
  uip_ipaddr(ipaddr, 255,255,255,0);
  uip_setnetmask(ipaddr);

  // Initialize the HTTP server.
  httpd_init();

  /** web server */
  
  
  
  // Init touch screen
  TouchScrInit();

//  // Why here ??? 
//  //
  FIO0DIR_bit.P0_11 = 1;
  FIO0DIR_bit.P0_19 = 1;
  
  
  int isonbutton1=-1;
  int isonbutton2=-1;

   

  while (1) {
    
/** web server */
    uip_len = tapdev_read(uip_buf);
    if(uip_len > 0)
    {
      if(BUF->type == htons(UIP_ETHTYPE_IP))
      {
	      uip_arp_ipin();
	      uip_input();
	      /* If the above function invocation resulted in data that
	         should be sent out on the network, the global variable
	         uip_len is set to a value > 0. */
	      if(uip_len > 0)
        {
	        uip_arp_out();
	        tapdev_send(uip_buf,uip_len);
	      }
      }
      else if(BUF->type == htons(UIP_ETHTYPE_ARP))
      {
        uip_arp_arpin();
	      /* If the above function invocation resulted in data that
	         should be sent out on the network, the global variable
	         uip_len is set to a value > 0. */
	      if(uip_len > 0)
        {
	        tapdev_send(uip_buf,uip_len);
	      }
      }
    }
    else if(timer_expired(&periodic_timer))
    {
      timer_reset(&periodic_timer);
      for(i = 0; i < UIP_CONNS; i++)
      {
      	uip_periodic(i);
        /* If the above function invocation resulted in data that
           should be sent out on the network, the global variable
           uip_len is set to a value > 0. */
        if(uip_len > 0)
        {
          uip_arp_out();
          tapdev_send(uip_buf,uip_len);
        }
      }
#if UIP_UDP
      for(i = 0; i < UIP_UDP_CONNS; i++) {
        uip_udp_periodic(i);
        /* If the above function invocation resulted in data that
           should be sent out on the network, the global variable
           uip_len is set to a value > 0. */
        if(uip_len > 0) {
          uip_arp_out();
          tapdev_send();
        }
      }
#endif /* UIP_UDP */
      /* Call the ARP timer function every 10 seconds. */
      if(timer_expired(&arp_timer))
      {
        timer_reset(&arp_timer);
        uip_arp_timer();
      }
    }
/** web server */    
    
    GLCD_Move_Cursor(Touch_data.X_cursor, Touch_data.Y_cursor);
    
    isonbutton1 = check_if_coursor_in_rectangle(Up_left_Button.X_coord, Up_left_Button.Y_coord, Up_left_Button.length, Up_left_Button.height);
    isonbutton2 = check_if_coursor_in_rectangle(Up_right_Button.X_coord, Up_right_Button.Y_coord, Up_right_Button.length, Up_right_Button.height);
    if((isonbutton1)&(screen_state==-1)&(screen_state_is_changing==-1)){
      screen_state=1;
      screen_state_is_changing=1;
    }
    else if((!isonbutton1)&(screen_state==1)&(screen_state_is_changing=-1)){
      screen_state=-1;
      screen_state_is_changing=1;
    } 

    if((screen_state==1)&(screen_state_is_changing==1)){
  
  GLCD_Ctrl (FALSE);
  //Load the "*.c" file containing the logo
  GLCD_Init (NULL, NULL);
//GLCD_Init (Black_bgPic.pPicStream, NULL);
  GLCD_LoadPic (1, 1, &button_lightgreyPic, NULL);
  GLCD_LoadPic (320-80, 1, &button_lightgreyPic, NULL);
  GLCD_Ctrl (TRUE);
   // Init Cursor
  GLCD_Cursor_Dis(0);
  GLCD_Copy_Cursor ((Int32U *)Cursor, 0, sizeof(Cursor)/sizeof(Int32U));
  GLCD_Cursor_Cfg(CRSR_FRAME_SYNC | CRSR_PIX_32);
  GLCD_Move_Cursor(Touch_data.X_cursor, Touch_data.Y_cursor);
  GLCD_Cursor_En(0);
  
  
  screen_state_is_changing=-1;
//      P_P_value();
    }
      else if((screen_state==-1)&(screen_state_is_changing==1)){
  GLCD_Ctrl (FALSE);
  //Load the "*.c" file containing the logo
  GLCD_Init (NULL, NULL);
//  GLCD_Init (Black_bgPic.pPicStream, NULL);
  GLCD_LoadPic (1, 1, &button_lightgreyPic, NULL);
  GLCD_LoadPic (320-80, 1, &button_lightgreyPic, NULL);
  GLCD_Ctrl (TRUE);
//    P_P_value();
 // Init Cursor
  GLCD_Cursor_Dis(0);
  GLCD_Copy_Cursor ((Int32U *)Cursor, 0, sizeof(Cursor)/sizeof(Int32U));
  GLCD_Cursor_Cfg(CRSR_FRAME_SYNC | CRSR_PIX_32);
  GLCD_Move_Cursor(Touch_data.X_cursor, Touch_data.Y_cursor);
  GLCD_Cursor_En(0);
  screen_state_is_changing=-1;
      }
  
    if(screen_state==1){
    LCD_Config_Screen();
    }
    
    if(screen_state==-1){
    LCD_Main_Screen();
    P_P_value();
    }
    
  
  };
}








int between(float input, float lower_limit, float upper_limit){
  
  if((input>=lower_limit)&&(input<=upper_limit))
    return true;
  else
    return false;

}

void Program_Init (){
  // Init system
  Sys_Init();
  
 // Init clock
  InitClock();
 // Init VIC
  VIC_Init();
 // Init DAC
  initialize_dac();
  initialize_adc();
 // Init LED
  initialize_led();
 //Init GPIO
  initialize_relays();
 // Init Timer0
  initialize_timer();
  LCD_Init();
  Touch_data.cursor_stay = true;
  
 // Init Cursor
  GLCD_Cursor_Dis(0);
  GLCD_Copy_Cursor ((Int32U *)Cursor, 0, sizeof(Cursor)/sizeof(Int32U));
  GLCD_Cursor_Cfg(CRSR_FRAME_SYNC | CRSR_PIX_32);
  GLCD_Move_Cursor(Touch_data.X_cursor, Touch_data.Y_cursor);
  GLCD_Cursor_En(0);
//   

}

void LCD_Init(){
  SDRAM_Init();
  GLCD_Ctrl (FALSE);
  //Load the "*.c" file containing the logo
  GLCD_Init (NULL, NULL);
//  GLCD_Init (Black_bgPic.pPicStream, NULL);
  GLCD_LoadPic (1, 1, &button_lightgreyPic, NULL);
  GLCD_LoadPic (320-80, 1, &button_lightgreyPic, NULL);
  GLCD_Ctrl (TRUE);
  
}

void LCD_Main_Screen(){
 

  
  GLCD_SetFont(&Terminal_18_24_12,0xFFFFFF,0x505050);
  GLCD_SetWindow(95, 10, 250, 40);
  GLCD_TextSetPos(0,0);
  printf("MAIN SCREEN");
  


}

void LCD_Config_Screen(){
 

  
  GLCD_SetFont(&Terminal_18_24_12,0xFFFFFF,0x505050);
  GLCD_SetWindow(95, 10, 250, 40);
  GLCD_TextSetPos(0,0);
  printf("__CONFIG__ ");
  


}

void P_P_value(){
    
  
  GLCD_SetFont(&Terminal_18_24_12,0xFFFFFF,0x505050);
  GLCD_SetWindow(X_Left, Y_Up, X_Right, Y_Down);
  GLCD_TextSetPos(0,0);
  //printf("Touch : %5d",Touch_data.touched);
  printf("Coords: X %5d, Y %5d",Touch_data.X ,Touch_data.Y );
  
  
  // Write cursor data 
  GLCD_SetWindow(X_Left, Y_Up+35, X_Right, Y_Down+35);
  GLCD_TextSetPos(0,0);
  //printf("Touch : %5d",Touch_data.touched);
  printf("Cursor: X %5d, Y %5d",Touch_data.X_cursor ,Touch_data.Y_cursor);
  //printf("Time      =   %d S", real_time.second);
  
  
  GLCD_SetFont(&Terminal_18_24_12,0xFFFFFF,0x505050);
  GLCD_SetWindow(X_Left, Y_Up+Linespace, X_Right, Y_Down+Linespace);
  GLCD_TextSetPos(0,0);
  //printf("Time      =    S");
  printf("Time  =   %3d:%3d", (real_time.second), (real_time.millisecond));
  
  
  GLCD_SetFont(&Terminal_18_24_12,0xFFFFFF,0x505050);
  GLCD_SetWindow(X_Left, Y_Up+Linespace+35, X_Right, Y_Down+Linespace+35);
  GLCD_TextSetPos(0,0);
  //printf("Time      =    S");
  printf("Vpp  max:%.02f, min:%.02f", (ADC_p_p.v_max), (ADC_p_p.v_min));
  
  
    // turn on led 2 if screen is touched
    //touch_scr_detect_touch(Touch_data.X);
  if((Touch_data.touched)&(led_status(2)))
      toggle_led(2);
  else if((!Touch_data.touched)&(!led_status(2)))
      toggle_led(2);
}