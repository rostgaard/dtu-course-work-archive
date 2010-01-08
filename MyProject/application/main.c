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


//float ADdata[2] = {0.0,0.0};    //([y(k-1) y(k)]) => ADdata(1)=y(k-1), ADdata(0)=y(k)
struct ADdata_t ADdata;
struct ADC_p_p_t ADC_p_p;


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
void ADC_Intr_Handler ();
void Timer0IntrHandler ();

float Frequency_with_interpolation();
float Frequency_without_interpolation();

void update_alpha();
float ADfilter(Int32U Data);

void LCD_Init();
void LCD_Write();

void P_P_value();


int between(float input, float lower_limit, float upper_limit);


extern FontType_t Terminal_9_12_6;
extern FontType_t Terminal_18_24_12;
extern float frequency;

/*************************************************************************
 * Function Name: Timer0IntrHandler
 * Parameters: none
 *
 * Return: none
 *
 * Description: Timer 0 interrupt handler
 *
 *************************************************************************/




int main(void)
{
  Program_Init();
  

  LCD_Init();
 // Reset ADdata struct values
  ADdata.v_current = 0.0;
  ADdata.v_previous = 0.0;
 //Setup VIC to respond to VIC_TIMER0 and call Timer0IntrHandler()
  VIC_SetVectoredIRQ(Timer0IntrHandler,1,VIC_TIMER0);
  VIC_SetVectoredIRQ(ADC_Intr_Handler,0,VIC_AD0);
  VICINTENABLE |= 1UL << VIC_AD0;  
  VICINTENABLE |= 1UL << VIC_TIMER0;
 // Start Timer
  timer0_start();
  adc_start();
 //Enable interrupts
  __enable_interrupt();
  update_alpha(); //sets the alpha value for the low-pass filter 
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
  

}

void LCD_Init(){
  SDRAM_Init();
  GLCD_Ctrl (FALSE);
  //Load the "*.c" file containing the logo
  GLCD_Init (Black_bgPic.pPicStream, NULL);
  GLCD_LoadPic (1, 1, &button_lightgreyPic, NULL);
  GLCD_LoadPic (320-80, 1, &button_lightgreyPic, NULL);
  GLCD_Ctrl (TRUE);
  
}

void LCD_Write(){
 
  GLCD_SetFont(&Terminal_18_24_12,0xFFFFFF,0x505050);
  GLCD_SetWindow(X_Left, Y_Up, X_Right, Y_Down);
  GLCD_TextSetPos(0,0);
  printf("F      =   %f Hz", f_out);
  
  GLCD_SetFont(&Terminal_18_24_12,0xFFFFFF,0x505050);
  GLCD_SetWindow(95, 10, 250, 40);
  GLCD_TextSetPos(0,0);
  printf("MAIN SCREEN");
}

void P_P_value(){
  GLCD_SetFont(&Terminal_18_24_12,0xFFFFFF,0x505050);
  GLCD_SetWindow(X_Left, Y_Up+Linespace, X_Right, Y_Down+Linespace);
  GLCD_TextSetPos(0,0);
  printf("P_rms  =   %f VA", f_out*3.0);
}