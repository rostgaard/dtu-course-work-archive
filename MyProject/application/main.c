#include <intrinsics.h>
#include <stdio.h>
#include "board.h"
#include "sys.h"
#include "config.h"
#include "sdram_32M_16bit_drv.h"
#include "drv_glcd.h"

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

unsigned long timetick =0;
Int32U Data=0;

int channel = 0;
int adcCLK = 0;

float alpha;
float frequency;
float f_out;
int tick_count = 0;
int ADfilter_enable = true;
int linear_inerpolaion = true;
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
void LCD_Write();
void P_P_value();
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





int main(void)
{
  
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
  
  
  // Init touch screen
  TouchScrInit();

  FIO0DIR_bit.P0_11 = 1;
  FIO0DIR_bit.P0_19 = 1;
  
  
  
  while (1) {

    LCD_Write();
    P_P_value();

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
//  InitClock();
 // Init VIC
//  VIC_Init();
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

void LCD_Write(){
 
  GLCD_SetFont(&Terminal_18_24_12,0xFFFFFF,0x505050);
  GLCD_SetWindow(X_Left, Y_Up, X_Right, Y_Down);
  GLCD_TextSetPos(0,0);
  //printf("Touch : %5d",Touch_data.touched);
  printf("Coords: X %5d, Y %5d",Touch_data.X ,Touch_data.Y );
  //printf("Time      =   %d S", real_time.second);
  
  GLCD_SetFont(&Terminal_18_24_12,0xFFFFFF,0x505050);
  GLCD_SetWindow(95, 10, 250, 40);
  GLCD_TextSetPos(0,0);
  printf("MAIN SCREEN");
  
  // turn on led 2 if screen is touched
    touch_scr_detect_touch(Touch_data.X);
  if((Touch_data.touched)&(led_status(2)))
      toggle_led(2);
  else if((!Touch_data.touched)&(!led_status(2)))
      toggle_led(2);

}

void P_P_value(){
  GLCD_SetFont(&Terminal_18_24_12,0xFFFFFF,0x505050);
  GLCD_SetWindow(X_Left, Y_Up+Linespace, X_Right, Y_Down+Linespace);
  GLCD_TextSetPos(0,0);
  //printf("Time      =    S");
  printf("Time  =   %4d mS", (real_time.second));
}