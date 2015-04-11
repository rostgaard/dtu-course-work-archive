#include "interrupt_handlers.h"
#include "frequency.h"
#include "gpio.h"
#include "dac.h"
#include "adc.h"
#include "timer.h"
#include "zerocrossing.h"
#include "config.h"
#include "board.h"
#include "touch_scr.h"

//External structs 
extern struct ADdata_t ADdata;
extern struct ADC_p_p_t ADC_p_p;
extern struct Touch_data_t Touch_data;


//External variables
extern float frequency;
extern float f_out;
extern Int32U Data;
extern float AD_zerocrossing_delay[2];   //([y(k-1) y(k)]) just after the zero-crossing accured
extern int tick_count;
extern int linear_inerpolaion;
extern int blink_timer;
extern int dac_timer;
extern float zero_crossing_detection_level;


//External fiunctions

extern float Frequency_with_interpolation();
extern float Frequency_without_interpolation();
extern float ADfilter(Int32U Data);

Int32U tmp = 0;
extern int measurement_status;

extern struct time_t real_time = {0,0};


//Starting definition of interrupt Handlers 
//


/*************************************************************************
 * Function Name: ADC_Inter_Handler
 * Parameters: none
 *
 * Return: none
 *
 * Description: ADC interrupt handler
 *
 *************************************************************************/
void ADC_Inter_Handler ()
{
  adc_stop();
  Touch_data.X_buffer = ADDR0_bit.RESULT;
  Touch_data.Y_buffer = ADDR1_bit.RESULT;
  tmp = ADDR3_bit.RESULT;
  
    
  ADdata.v_current = (Int32U)ADfilter(ADDR2_bit.RESULT);
  Data = (Int32U)ADdata.v_current;
  
  if(Data>ADC_p_p.v_instance_max_bits){
    ADC_p_p.v_instance_max_bits=Data;
  }
  else if(Data<ADC_p_p.v_instance_min_bits){
    
    ADC_p_p.v_instance_min_bits=Data;
  }
  
  // clear interrupt
  VICADDRESS = 0;

  
}




/*************************************************************************
 * Function Name: Timer0IntrHandler
 * Parameters: none
 *
 * Return: none
 *
 * Description: Timer 0 interrupt handler
 *
 *************************************************************************/
void Timer0IntrHandler (void)
{
//   ========== LCD - touch meassurement loop: START
  if(Touch_data.state==0)
  {
    Touch_data.X=Touch_data.X_buffer;
    set_touch_meassure_ch(Y_ch); 
    Touch_data.state++;
  }
  else if(Touch_data.state==1)
  {
    Touch_data.Y=Touch_data.Y_buffer;
    //set_touch_meassure_ch(wait);  
    Touch_data.state++;
  }
  else
  {
    touch_scr_detect_touch(Touch_data.X_buffer);
    set_touch_meassure_ch(X_ch); 
    Touch_data.state=0;
  }
      //touch_ADC_setup_Loop();
//  set_touch_meassure_ch(X_ch);
//  Touch_data.X=Touch_data.X_buffer;
////  Touch_data.Y=Touch_data.Y_buffer;
  // ========== LCD - touch meassurement loop: STOP
  
//  if (Touch_data.state==(Y_ch || confirm))    
  adc_start();  //Start the ADC, ADC will be done before next timer interrupt
                //it has higer priority and will therefore be handlet first 

  
  tick_count++;
  real_time.ms_tick_count++;
  
  if((real_time.ms_tick_count/1000)%TIMER0_TICK_PER_SEC == 0) {
      real_time.millisecond++;
      real_time.ms_tick_count = 0;
      if(real_time.millisecond == 1000) {
        real_time.second++;
        real_time.millisecond=0;
      }
  }

     

  //frequency calculation: START
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
    
    ADC_p_p.v_min=ADC_p_p.v_instance_min_bits*(240.0/1023.0);
    ADC_p_p.v_max=ADC_p_p.v_instance_max_bits*(240.0/1023.0);
    
    ADC_p_p.v_instance_min_bits=zero_crossing_detection_level;
    ADC_p_p.v_instance_max_bits=zero_crossing_detection_level;    
 
    
  }
  //frequency calculation: STOP
  
  dac_timer++;
  if(dac_timer == DAC_MAXIMUM) {
    dac_timer = 0;
    
    blink_timer++;
    if(blink_timer == FREQUENCY/5) {
      toggle_led(1);
//      toggle_led(2);
      blink_timer = 0;
      f_out = frequency;

      if(frequency>=55.0 && relays_status(bulb) != 1) {
//        toggle_relays(bulb, 1);
      }
      else if(frequency<=45.0 && relays_status(bulb) != 0){
//        toggle_relays(bulb, 0);
      }
      
    }
  }
  // Write data to DAC
  dac_write(Data);
  // clear interrupt
  timer0_interrupt_reset();
  VICADDRESS = 0;

}