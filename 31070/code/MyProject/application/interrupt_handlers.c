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
#include <math.h>


//External structs 
extern struct ADdata_t ADdata;
extern struct Touch_data_t Touch_data;
extern struct ADC_p_p_t ADC_p_p;

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
extern float ADfilter(Int32U current_raw_measurement);
extern void  calculate_frequency(int tick_count);

extern void update_instance_p_p_values(Int32U current_raw_measurement);
extern void calculate_p_p_values();


extern int measurement_status;


float accumulator_max=0.0;
float accumulator_min=0.0;

//float I_scale = 3.1/1023.;
float I_scale_PP = 1./1023.*1.414213;
float V_scale_PP = 236./341.*1.414213;
float I_scale    = 1./1023.;
float V_scale    = 236./341.;

float V_RMS=0.0;
float V_RMS_2_accumulator=0.0;
float I_RMS=0.0;
float I_RMS_2_accumulator=0.0;
float P_RMS=0.0;
float P_RMS_2_accumulator=0.0;

extern int Relay1_button_state;
extern int Relay2_button_state;


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
  

  
  touch_scr_detect_touch(Touch_data.Y);
  //update_touch_coordinates();
  
  ADdata.I_current = ADDR3_bit.RESULT;
  ADdata.previous_raw_measurement = ADdata.current_raw_measurement;
  ADdata.current_raw_measurement = ADDR2_bit.RESULT;
    
//  ADdata.current_filtered_measurement = (Int32U)ADfilter(ADdata.current_raw_measurement);
//  ADdata.current_filtered_measurement = ADfilter(ADdata.current_raw_measurement);
//  Data = ADdata.current_raw_measurement;
//  Data = ADdata.current_filtered_measurement;
  
  ADdata.previous_filtered_measurement = ADdata.current_filtered_measurement;
  ADdata.current_filtered_measurement = ADfilter(ADdata.current_raw_measurement);
  
  Data = (Int32U)ADdata.current_filtered_measurement;
  
  
  //update_instance_p_p_values(ADdata.current_raw_measurement);
  
  V_RMS_2_accumulator += pow(Bits_2_Grid_Voltage(ADdata.current_raw_measurement),2.0);
  I_RMS_2_accumulator += pow((ADdata.I_current-512.)*I_scale,2.0);
  P_RMS_2_accumulator += (((ADdata.current_raw_measurement-512.)*V_scale_PP)*((ADdata.I_current-512.)*I_scale_PP));
//V_RMS_2_accumulator += pow(Data, 2.0);  
  
//    V_RMS_2_accumulator += pow(ADdata.current_raw_measurement,2.0);

//  FIO0PIN_bit.P0_11 = 0;
 
  // clear interrupt
  VICADDRESS = 0;

  
}

int httpd_tick_count = 0;


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
  update_touch_coordinates();
//  FIO0PIN_bit.P0_19 = 1;
  
//   ========== LCD - touch meassurement loop: START
  
  switch_touch_meassure_channel();

  // ========== LCD - touch meassurement loop: STOP
  
//  if (Touch_data.state==(Y_ch || confirm))   
  
//    FIO0PIN_bit.P0_11 = 1;
  adc_start();  //Start the ADC, ADC will be done before next timer interrupt
                //it has higer priority and will therefore be handlet first 

  httpd_tick_count++;
  if((httpd_tick_count)%(TIMER0_TICK_PER_SEC/HTTPD_TICK_PER_SEC) == 0) {
    httpd_tick();
    httpd_tick_count = 0;
  }
  
  
  tick_count++;

  update_real_time();

  //calculate_p_p_values();   
  
  
  
  
  //frequency calculation: START
  
  //frequency calculation: STOP
  
  
  
    if(detect_ZeroCrossing(ADdata.previous_filtered_measurement, ADdata.current_filtered_measurement))
    {
      //Calculate RMS value
      V_RMS = calculate_X_RMS(V_RMS_2_accumulator);
      I_RMS = calculate_X_RMS(I_RMS_2_accumulator);
      P_RMS = ((P_RMS_2_accumulator)/(tick_count));
      //CAlculate peak value from RMS value
      ADC_p_p.v_max = sqrt(2)*V_RMS;
      ADC_p_p.v_min = -sqrt(2)*V_RMS;
      //Clear RMS accumulator 
      V_RMS_2_accumulator=0.0;
      I_RMS_2_accumulator=0.0;
      P_RMS_2_accumulator=0.0;
      calculate_frequency(tick_count);      
      tick_count=0;
    }
 
  
  
  
  dac_timer++;
  if(dac_timer == DAC_MAXIMUM) {
    dac_timer = 0;
    
    blink_timer++;
    if(blink_timer == FREQUENCY/5) {
      toggle_led(1);
//      toggle_led(2);
      blink_timer = 0;
      f_out = frequency;
      
      
      
    if(Relay1_button_state==1)
    FIO0PIN_bit.P0_11 = 1;
    else{

      if(frequency>=RELAY_ON_FREQUENCY && relays_status(bulb) != 1) {
        toggle_relays(bulb, 1);
      }
      else if(frequency<=RELAY_OFF_FREQUENCY && relays_status(bulb) != 0){
        toggle_relays(bulb, 0);
      }
 }
 
 
 
     if(Relay2_button_state==1)
    FIO0PIN_bit.P0_19 = 1;
    else{

      if(frequency>=RELAY_ON_FREQUENCY && relays_status(socket) != 1) {
        toggle_relays(socket, 1);
      }
      else if(frequency<=RELAY_OFF_FREQUENCY && relays_status(socket) != 0){
        toggle_relays(socket, 0);
      }
 }
      
    }
  }
  // Write data to DAC
  dac_write(Data);
  // clear interrupt
  timer0_interrupt_reset();
//  FIO0PIN_bit.P0_19 = 0;
//    FIO0PIN_bit.P0_11 = 0;
  VICADDRESS = 0;

}

float calculate_X_RMS(float accumulator_X_2){
  
  return sqrt((accumulator_X_2/tick_count));
}

float Bits_2_Grid_Voltage(int Bits_reslut){
  //Calibrating by factor of 0.404//*0.679226//*0.6920821
  return (Bits_reslut-512.0)*V_scale;
  
}




