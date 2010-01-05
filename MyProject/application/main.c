
#include <intrinsics.h>
#include <stdio.h>
#include "board.h"
#include "sys.h"
#include "sdram_32M_16bit_drv.h"
/*#include "drv_glcd.h"
#include "logo.h"
#include "Cursor.h"
#include "smb380_drv.h"*/


#define DAC_MAXIMUM  1024 // 2^10
#define FREQUENCY 50
#define TIMER0_TICK_PER_SEC  FREQUENCY*DAC_MAXIMUM
#define SAMPLE_FREQUENCY 10000
#define dT 1/SAMPLE_FREQUENCY
#define Fc 50
#define VREF 3.3

unsigned long timetick =0;
Int32U Data=50;

void start_timer();
void stop_timer();
void initialize_timer();
void ADC_Intr_Handler ();
void Timer0IntrHandler ();
void initialize_led();
void initialize_dac();
void initialize_adc();
void toggle_led(int led_number);
void start_adc();

/*************************************************************************
 * Function Name: Timer0IntrHandler
 * Parameters: none
 *
 * Return: none
 *
 * Description: Timer 0 interrupt handler
 *
 *************************************************************************/

int blink_timer = 0;
int dac_timer = 0;



//void GetTimeTick (void)
//{
//   timetick = T0TC_bit;
//   
//
//}


int main(void)
{
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
 // Init Timer0
  initialize_timer();
  
 //Setup VIC to respond to VIC_TIMER0 and call Timer0IntrHandler()
  VIC_SetVectoredIRQ(Timer0IntrHandler,0,VIC_TIMER0);
  VIC_SetVectoredIRQ(ADC_Intr_Handler,0,VIC_AD0);
  VICINTENABLE |= 1UL << VIC_AD0;  
  VICINTENABLE |= 1UL << VIC_TIMER0;
 // Start Timer
  start_timer();
  start_adc();
 //Enable interrupts
  __enable_interrupt();

  while (1) {
    ;
  };
  
}

void initialize_led()
{
  // Init USB Link  LED
  FIO1DIR_bit.P1_18 = 1;
  FIO1DIR_bit.P1_13 = 1;
  
  //Set starting value of led  
  FIO1PIN_bit.P1_18 = 0;
  FIO1PIN_bit.P1_18 = 1;
}
void initialize_dac()
{
  PINMODE1_bit.P0_26 = 2; 
  PINSEL1_bit.P0_26 = 2;
}
void ADC_Intr_Handler ()
{
  //Int32U Data;
  
  AD0CR_bit.START = 0;
  Data = AD0GDR_bit.RESULT;

 AD0CR_bit.START = 1;
  // clear interrupt
  VICADDRESS = 0;
 
}
void initialize_adc()
{
  
  // Init ADC
  PCONP_bit.PCAD = 1;         // Enable ADC clocks
  AD0CR_bit.PDN  = 1;         // converter is operational
  AD0CR_bit.START = 0;
  
  ADINTEN |= 1UL << 1;
  
  AD0CR_bit.SEL  = 1UL<<2;    // select Ch1
  AD0CR_bit.CLKDIV = (int)(SYS_GetFpclk(ADC_PCLK_OFFSET)/ TIMER0_TICK_PER_SEC);
  AD0CR_bit.BURST  = 0;       // disable burst
  AD0CR_bit.CLKS   = 0;       // 10 bits resolution

  // clear all pending interrupts
  while(ADSTAT_bit.ADINT)
  {
    volatile Int32U Dummy = AD0GDR;
  }
 
}

void start_adc(){
  AD0CR_bit.START = 1;
}

void initialize_timer()
{
  // Enable TIM0 clocks
  PCONP_bit.PCTIM0 = 1; // enable clock


  // Init Time0
  stop_timer();
  T0TCR_bit.CR = 1;     // set reset
  T0TCR_bit.CR = 0;     // release reset
  T0CTCR_bit.CTM = 0;   // Timer Mode: every rising PCLK edge
  T0MCR_bit.MR0I = 1;   // Enable Interrupt on MR0
  T0MCR_bit.MR0R = 1;   // Enable reset on MR0
  T0MCR_bit.MR0S = 0;   // Disable stop on MR0
  // set timer 0 period
  T0PR = 0;
  T0MR0 = SYS_GetFpclk(TIMER0_PCLK_OFFSET)/(TIMER0_TICK_PER_SEC);
  // init timer 0 interrupt
  T0IR_bit.MR0INT = 1;  // clear pending interrupt
}

void start_timer() {
  T0TCR_bit.CE = 1;     // counting Enable  
}

void stop_timer() {
  T0TCR_bit.CE = 0;     // counting  disable
}

void toggle_led(int led_number) {
  switch(led_number) {
  case 1:
    FIO1PIN ^= (1UL<<18);
    break;
  case 2:
    FIO1PIN ^= (1UL<<13);
    break;
  default:
    break;
  }
}

void Timer0IntrHandler (void)
{
  
  dac_timer++;
  if(dac_timer == DAC_MAXIMUM) {
    dac_timer = 0;
    
    blink_timer++;
    if(blink_timer == FREQUENCY) {
      toggle_led(1);
      toggle_led(2);
      blink_timer = 0;
    }
  }
  //DACR_bit.VALUE = dac_timer;
  DACR_bit.VALUE = Data;
  // clear interrupt
  T0IR_bit.MR0INT = 1;
  VICADDRESS = 0;
}