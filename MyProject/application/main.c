#include <intrinsics.h>
#include <stdio.h>

#include "config.h"
#include "includes.h"

#include "adc.h"
#include "zerocrossing.h"
#include "interrupt_handlers.h"
#include "timer.h"
#include "filter.h"
#include "touch_scr.h"



struct ADdata_t ADdata = {0,0,0,0,0.0,0.0};
struct ADC_p_p_t ADC_p_p;
extern struct time_t real_time;
extern struct Touch_data_t Touch_data;
extern Button_t Up_left_Button;
extern Button_t Up_right_Button;
extern Button_t Relay1_Button;
extern Button_t Relay2_Button;

unsigned long timetick =0;
Int32U Data=0;

int channel = 0;
int adcCLK = 0;

float alpha;
float alpha_HP;
float frequency;
float f_out;
int tick_count = 0;
int ADfilter_enable = true;
int HP_filter_enable = false;
int linear_inerpolaion = true;
int blink_timer = 0;
int dac_timer = 0;
Int32U X_Left =5;
Int32U X_Right =310;
Int32U Y_Up =45;          //Just below the top bar (with buttons and screen name)
Int32U Y_Down =83;        
Int32U Linespace = 35*2;
float AD_zerocrossing_delay[2] = {0.0,0.0};   //([y(k-1) y(k)]) just after the zero-crossing accured


void Program_Init ();
void LCD_Init();
void LCD_Main_Screen();
void P_P_value();
void LCD_Config_Screen();
int between(float input, float lower_limit, float upper_limit);
void update_scr_state();
void change_scr();
void load_scr_graphics(Int32U *pBackground_painting);
void enable_line_of_text(float line_nr, int font_size, LdcPixel_t Color, LdcPixel_t BackgndColor);
void update_relay_buttons_state();
void activate_response_to_relay_buttons();

extern void ADC_Inter_Handler ();
extern void Timer0IntrHandler ();
extern float Frequency_with_interpolation();
extern float Frequency_without_interpolation();
extern void update_alpha();
extern float ADfilter(Int32U current_raw_measurement);
extern void TouchScrInit();


extern FontType_t Terminal_9_12_6;
extern FontType_t Terminal_18_24_12;
extern float frequency;
extern int blink_timer;

extern float V_RMS;
extern float I_RMS;
extern float P_RMS;



extern Bmp_t button_relay_off_lightgreyPic;
extern Bmp_t button_relay_on_lightgreyPic;

int screen_state = 0;   //0 - MAIN SCR., 1 - CONFIG SCR., 2 - TEST SCR.
int screen_state_is_changing = 1;     //0 - scr is NOT changing, 1 - scr IS changing

  // The target of the two buttons
int isonbutton1=0;
int isonbutton2=0;

int Relay1_button_state = 0;
int Relay1_button_state_is_changing =0;
int Relay2_button_state = 0;
int Relay2_button_state_is_changing =0;

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
//  clock_init(2);

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
    
                     
                     
             
    

    
       Up_left_Button.was_pushed =  Up_left_Button.is_pushed;
       Up_right_Button.was_pushed =  Up_right_Button.is_pushed;
       
       Relay1_Button.was_pushed =  Relay1_Button.is_pushed;
       Relay2_Button.was_pushed =  Relay2_Button.is_pushed;       
       
       Up_left_Button.is_pushed = (Touch_data.touched)&(check_if_coursor_in_rectangle(Up_left_Button.X_coord, Up_left_Button.Y_coord, Up_left_Button.length, Up_left_Button.height));
       Up_right_Button.is_pushed = (Touch_data.touched)&(check_if_coursor_in_rectangle(Up_right_Button.X_coord, Up_right_Button.Y_coord, Up_right_Button.length, Up_right_Button.height));
 
     
       
      
       Relay1_Button.is_pushed = ((Touch_data.touched)&(check_if_coursor_in_rectangle(Relay1_Button.X_coord, Relay1_Button.Y_coord, Relay1_Button.length, Relay1_Button.height)));
       Relay2_Button.is_pushed = (Touch_data.touched)&(check_if_coursor_in_rectangle(Relay2_Button.X_coord, Relay2_Button.Y_coord, Relay2_Button.length, Relay2_Button.height));        
       
       
       update_relay_buttons_state();
                 
       activate_response_to_relay_buttons();
       
    
    if(Touch_data.touched) {
      
       GLCD_Move_Cursor(Touch_data.X_cursor, Touch_data.Y_cursor);
    }
    

     
    update_scr_state();

    /**
     * Changes the graphics on the scren upon pagechange
     */ 
    
    
      change_scr();
      

    
  }
  
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
 // initialize_dac();
  initialize_adc();
 // Init LED
  initialize_led();
 //Init GPIO
  initialize_relays();
 // Init Timer0
  initialize_timer();
  LCD_Init();
  Touch_data.cursor_stay = true;
  
  // Center cursor on screen
  Touch_data.X_cursor = C_GLCD_H_SIZE/2;
  Touch_data.Y_cursor = C_GLCD_V_SIZE/2;
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
  
  enable_line_of_text(0, 0, 0xFFFFFF,0x505050);
  printf("Voltage, RMS: %0.4f V", V_RMS);
         
  enable_line_of_text(1, 0, 0xFFFFFF,0x505050);
  printf("Frequency: %0.4f Hz", frequency);
  
  enable_line_of_text(2, 0, 0xFFFFFF,0x505050);
  printf("Power  = %0.2f", P_RMS);
  
  enable_line_of_text(3, 0, 0xFFFFFF,0x505050);
  if(FIO0PIN_bit.P0_11 == 1)
  printf("Relay 1: ON ");
  else if(FIO0PIN_bit.P0_11 == 0)
  printf("Relay 1: OFF");    
  
  enable_line_of_text(4, 0, 0xFFFFFF,0x505050);
  if(FIO0PIN_bit.P0_19 == 1)
  printf("Relay 2: ON ");
  else if(FIO0PIN_bit.P0_19 == 0)
  printf("Relay 2: OFF"); 

        activate_response_to_relay_buttons();


}

void LCD_Config_Screen(){
 

  
  GLCD_SetFont(&Terminal_18_24_12,0xFFFFFF,0x505050);
  GLCD_SetWindow(95, 10, 250, 40);
  GLCD_TextSetPos(0,0);
  printf("__CONFIG__ ");
  


}

void LCD_Test_Screen(){
 

  
  GLCD_SetFont(&Terminal_18_24_12,0xFFFFFF,0x505050);
  GLCD_SetWindow(95, 10, 250, 40);
  GLCD_TextSetPos(0,0);
  printf("TEST SCREEN");
  
  P_P_value();

}

void P_P_value(){
    
  
  enable_line_of_text(0, 1, 0xFFFFFF,0x505050);
  //printf("Touch : %5d",Touch_data.touched);
  printf("Coords: X %5d, Y %5d",Touch_data.X ,Touch_data.Y );
  
  
  // Write cursor data 
  enable_line_of_text(1, 1, 0xFFFFFF,0x505050);
  printf("Cursor: X %5d, Y %5d",Touch_data.X_cursor ,Touch_data.Y_cursor);
  //printf("Time      =   %d S", real_time.second);
  
  
  enable_line_of_text(2, 0, 0xFFFFFF,0x505050);
  //printf("Time      =    S");
  printf("Time  =  %3d:%2d", (real_time.second/60),(real_time.second%60));
  
  
  enable_line_of_text(3, 0, 0xFFFFFF,0x505050);
  //printf("Time      =    S");
  printf("Vpp  max:%.02f, min:%.02f", (ADC_p_p.v_max), (ADC_p_p.v_min));
  
  
    // turn on led 2 if screen is touched
    //touch_scr_detect_touch(Touch_data.X);
//  if((Touch_data.touched)&(led_status(2)))
//      toggle_led(2);
//  else if((!Touch_data.touched)&(!led_status(2)))
//      toggle_led(2);
}




void update_scr_state(){
  
  //For MAIN SCREEN (screen_state==0)
    if((Up_left_Button.is_pushed)&(!Up_left_Button.was_pushed)&(screen_state==0)&(screen_state_is_changing==0)){
      screen_state=2;
      screen_state_is_changing=1;
    } 
    else if((Up_right_Button.is_pushed)&(!Up_right_Button.was_pushed)&(screen_state==0)&(screen_state_is_changing==0)){
      screen_state=1;
      screen_state_is_changing=1;
    }
    
  //For CONFIG SCREEN (screen_state==1)
    else if((Up_left_Button.is_pushed)&(!Up_left_Button.was_pushed)&(screen_state==1)&(screen_state_is_changing==0)){
      screen_state=0;
      screen_state_is_changing=1;
    } 
    else if((Up_right_Button.is_pushed)&(!Up_right_Button.was_pushed)&(screen_state==1)&(screen_state_is_changing==0)){
      screen_state=2;
      screen_state_is_changing=1;
    }
    
      //For TEST SCREEN (screen_state==2)
    else if((Up_left_Button.is_pushed)&(!Up_left_Button.was_pushed)&(screen_state==2)&(screen_state_is_changing==0)){
      screen_state=1;
      screen_state_is_changing=1;
    } 
    else if((Up_right_Button.is_pushed)&(!Up_right_Button.was_pushed)&(screen_state==2)&(screen_state_is_changing==0)){
      screen_state=0;
      screen_state_is_changing=1;
    }
    
    
}

void change_scr(){
  
      if((screen_state==1)&(screen_state_is_changing==1)){
      load_scr_graphics(NULL);
        
      screen_state_is_changing=0;
   }
   else if((screen_state==0)&(screen_state_is_changing==1)){
      
      load_scr_graphics(NULL);
      screen_state_is_changing=0;
   }
   else if((screen_state==2)&(screen_state_is_changing==1)){
      
      load_scr_graphics(NULL);
      screen_state_is_changing=0;
   }
   
      
   
   if(screen_state==2 && (screen_state_is_changing==0) && (real_time.millisecond%50) == 0){
      LCD_Test_Screen();

   }
   if(screen_state==1 && (screen_state_is_changing==0) && (real_time.millisecond%50) == 0){
      LCD_Config_Screen();
   }
    
   if(screen_state==0 && (screen_state_is_changing==0) && (real_time.millisecond%50) == 0){
      LCD_Main_Screen();
      if(Relay1_button_state==1)
        GLCD_LoadPic (170, (int)(Y_Up+(3*34.0)), &button_relay_on_lightgreyPic, NULL);
      else if(Relay1_button_state==0)
        GLCD_LoadPic (170, (int)(Y_Up+(3*34.0)), &button_relay_off_lightgreyPic, NULL);
      
      if(Relay2_button_state==1)
        GLCD_LoadPic (170, (int)(Y_Up+(4*34.0)), &button_relay_on_lightgreyPic, NULL);
      else if(Relay2_button_state==0)
        GLCD_LoadPic (170, (int)(Y_Up+(4*34.0)), &button_relay_off_lightgreyPic, NULL);


   }

  
}


void load_scr_graphics(Int32U *pBackground_painting){
  
        GLCD_Ctrl (FALSE);
      //Load the "*.c" file containing the logo
      GLCD_Init (pBackground_painting, NULL);
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
  
  
}

/*
  Enables certain line, in wich we can write text, on LCD.
  Use "printf("")" afterwards to specify this text. */
void enable_line_of_text(float line_nr, int font_size, LdcPixel_t Color, LdcPixel_t BackgndColor){
  
  //font 0 (big) allows only 6 lines of text
  if(font_size==0){
  GLCD_SetFont(&Terminal_18_24_12,Color,BackgndColor);
  GLCD_SetWindow(X_Left, (int)(Y_Up+(line_nr*34.0)), X_Right, (int)(Y_Down+(line_nr*34.0)));
  GLCD_TextSetPos(0,0);
  }
  
  //font 1 (small) allows only 12 lines of text
  if(font_size==1){
  GLCD_SetFont(&Terminal_9_12_6,Color,BackgndColor);
  GLCD_SetWindow(X_Left, (int)(Y_Up+(line_nr*17.0)), X_Right, (int)(Y_Down+(line_nr*17.0)));
  GLCD_TextSetPos(0,0);
  }
}


void update_relay_buttons_state(){
  
         if((Relay1_Button.is_pushed) && (!Relay1_Button.was_pushed) && (Relay1_button_state==0)&(Relay1_button_state_is_changing==0)&(screen_state==0)){
         Relay1_button_state=1;
         Relay1_button_state_is_changing=1;
         }
       else if((Relay1_Button.is_pushed)&(!Relay1_Button.was_pushed)&(Relay1_button_state==1)&(screen_state_is_changing==0)&(screen_state==0)){
         Relay1_button_state=0;
         Relay1_button_state_is_changing=1;
        }
        
       if((Relay2_Button.is_pushed) && (!Relay2_Button.was_pushed) && (Relay2_button_state==0)&(Relay2_button_state_is_changing==0)&(screen_state==0)){
         Relay2_button_state=1;
         Relay2_button_state_is_changing=1;
         }
       else if((Relay2_Button.is_pushed)&(!Relay2_Button.was_pushed)&(Relay2_button_state==1)&(screen_state_is_changing==0)&(screen_state==0)){
         Relay2_button_state=0;
         Relay2_button_state_is_changing=1;
        }
}



void activate_response_to_relay_buttons(){
  
      if(Relay1_button_state==1 && Relay1_button_state_is_changing==1){
         toggle_led(2);
//         GLCD_LoadPic (170, (int)(Y_Up+(3*34.0)), &button_relay_on_lightgreyPic, NULL);
         Relay1_button_state_is_changing=0;
        }
      else if(Relay1_button_state==0 && Relay1_button_state_is_changing==1){
          toggle_led(2);
//          GLCD_LoadPic (170, (int)(Y_Up+(3*34.0)), &button_relay_off_lightgreyPic, NULL);
          Relay1_button_state_is_changing=0;
         } 
      
      
      if(Relay2_button_state==1 && Relay2_button_state_is_changing==1){
//         GLCD_LoadPic (170, (int)(Y_Up+(3*34.0)), &button_relay_on_lightgreyPic, NULL);
         Relay2_button_state_is_changing=0;
        }
      else if(Relay2_button_state==0 && Relay2_button_state_is_changing==1){
    
//          GLCD_LoadPic (170, (int)(Y_Up+(3*34.0)), &button_relay_off_lightgreyPic, NULL);
          Relay2_button_state_is_changing=0;
         } 
      
//       if(Relay1_button_state==1 && Relay1_button_state_is_changing==0 & (real_time.millisecond%500)){
//          GLCD_LoadPic (170, (int)(Y_Up+(3*34.0)), &button_relay_on_lightgreyPic, NULL);
//       }
//       else if((Relay1_button_state==0 && Relay1_button_state_is_changing==0) & (real_time.millisecond%500)){
//         GLCD_LoadPic (170, (int)(Y_Up+(3*34.0)), &button_relay_off_lightgreyPic, NULL);
//       }
}