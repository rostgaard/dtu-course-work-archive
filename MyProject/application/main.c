#include <intrinsics.h>
#include <stdio.h>
#include "board.h"
#include "sys.h"
#include "config.h"
#include "sdram_32M_16bit_drv.h"

#include "gpio.h"
#include "dac.h"
#include "adc.h"
#include "timer.h"
#include "zerocrossing.h"

unsigned long timetick =0;
Int32U Data=0;

int channel = 0;
int adcCLK = 0;
//float ADdata[2] = {0.0,0.0};    //([y(k-1) y(k)]) => ADdata(1)=y(k-1), ADdata(0)=y(k)
struct ADdata_t ADdata;
float alpha;
float frequency;
int tick_count = 0;
int ADfilter_enable = false;
int linear_inerpolaion = true;

float AD_zerocrossing_delay[2] = {0.0,0.0};   //([y(k-1) y(k)]) just after the zero-crossing accured

void ADC_Intr_Handler ();
void Timer0IntrHandler ();

float Frequency_with_interpolation();
float Frequency_without_interpolation();

void update_alpha();
float ADfilter(Int32U Data);

int between(float input, float lower_limit, float upper_limit);

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


int main(void)
{
  // Reset ADdata struct values
  ADdata.v_current = 0.0;
  ADdata.v_previous = 0.0;
  
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
    ;
  };
}

void ADC_Intr_Handler ()
{
  adc_stop();
  
  Data = (Int32U)ADfilter(AD0GDR_bit.RESULT);
 
  // clear interrupt
  VICADDRESS = 0;

}
void Timer0IntrHandler (void)
{

  adc_start();  //Start the ADC, ADC will be done before next timer interrupt
                //it has higer priority and will therefore be handlet first 
  tick_count++;
  if(detect_ZeroCrossing(ADdata.v_previous, ADdata.v_current) == true)
  {
    if(linear_inerpolaion == true){
      //Updating the array with the zero crossing delays 
      AD_zerocrossing_delay[1]=AD_zerocrossing_delay[0];
      AD_zerocrossing_delay[0]=ZeroCrossing_delays(ADdata.v_previous, ADdata.v_current);
      
      frequency = Frequency_with_interpolation(tick_count);
    }
    else{
      frequency = Frequency_without_interpolation(tick_count);
    }
    
    tick_count=0; 
  }
  
  dac_timer++;
  if(dac_timer == DAC_MAXIMUM) {
    dac_timer = 0;
    
    blink_timer++;
    if(blink_timer == FREQUENCY/5) {
      toggle_led(1);
      toggle_led(2);
      blink_timer = 0;
      
      if(frequency>=55.0 && relays_status(bulb) != 1) {
        toggle_relays(bulb, 1);
      }
      else if(frequency<=45.0 && relays_status(bulb) != 0){
        toggle_relays(bulb, 0);
      }
      
    }
  }
  // Write data to DAC
  dac_write(Data);
  // clear interrupt
  timer0_interrupt_reset();
  VICADDRESS = 0;
  
}
void update_alpha() {
    alpha=dT/(1/(2*PI*ADFILTER_CUT_OFF_FREQUENCY)+dT);
}
float ADfilter(Int32U x){
  
  //shifting data in the ADdata array ([y(k-1) y(k)]) => ADdata(1)=y(k-1), ADdata(0)=y(k)
//  ADdata.v_previous=ADdata.v_current;
//  ADdata.v_current=(alpha*x+(1-alpha)*ADdata.v_previous);

  if(ADfilter_enable==true){
      ADdata.v_previous=ADdata.v_current;
      ADdata.v_current=(alpha*x+(1-alpha)*ADdata.v_previous);
  }
  else{
      ADdata.v_previous=ADdata.v_current;
      ADdata.v_current=x;
  }



  //return filtered y(k)
    return (Int32U)ADdata.v_current;
  
}

float Frequency_with_interpolation(){
  
  return 1.0/((tick_count*dT)+(-AD_zerocrossing_delay[0]+AD_zerocrossing_delay[1]));
}

float Frequency_without_interpolation(){

  return 1.0/(dT*tick_count);
}

int between(float input, float lower_limit, float upper_limit){
  
  if((input>=lower_limit)&&(input<=upper_limit))
    return true;
  else
    return false;

}
