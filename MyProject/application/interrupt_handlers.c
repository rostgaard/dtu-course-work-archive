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
extern struct Touch_data_t Touch_data;


//External variables
extern float frequency;
extern float f_out;
extern Int32U Data;
extern float AD_zerocrossing_delay[2];   //([y(k-1) y(k)]) just after the zero-crossing accured
extern int tick_count;

extern int blink_timer;
extern int dac_timer;
extern float zero_crossing_detection_level;


//External fiunctions

extern float Frequency_with_interpolation();
extern float Frequency_without_interpolation();
extern float ADfilter(Int32U Data);
extern void  calculate_frequency(int tick_count);

extern void update_instance_p_p_values(Int32U Data);
extern void calculate_p_p_values();

Int32U tmp = 0;
extern int measurement_status;


float accumulator_max=0.0;
float accumulator_min=0.0;

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
//  FIO0PIN_bit.P0_11 = 1;
  adc_stop();
  
  touch_scr_detect_touch(Touch_data.X);
  update_touch_coordinates();
  
  tmp = ADDR3_bit.RESULT;
  
    
  ADdata.v_current = (Int32U)ADfilter(ADDR2_bit.RESULT);
  Data = (Int32U)ADdata.v_current;
  
  update_instance_p_p_values(Data);
  



//  FIO0PIN_bit.P0_11 = 0;
//  FIO0PIN_bit.P0_19 = 0;
 
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
  FIO0PIN_bit.P0_19 = 1;
  
//   ========== LCD - touch meassurement loop: START
  
  switch_touch_meassure_channel();

  // ========== LCD - touch meassurement loop: STOP
  
//  if (Touch_data.state==(Y_ch || confirm))    
  adc_start();  //Start the ADC, ADC will be done before next timer interrupt
                //it has higer priority and will therefore be handlet first 

  
  tick_count++;

  update_real_time();

  calculate_p_p_values();   

  if(detect_ZeroCrossing(ADdata.v_previous, ADdata.v_current) == true)
          tick_count=0;
    
    

  
  
  
  //frequency calculation: START
  calculate_frequency(tick_count);
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
  FIO0PIN_bit.P0_19 = 0;
  VICADDRESS = 0;

}




