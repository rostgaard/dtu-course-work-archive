#include "frequency.h"
#include "config.h"
#include "zerocrossing.h"
#include "adc.h"
#include "interrupt_handlers.h"

#include "board.h"

//External variables
extern float AD_zerocrossing_delay[2];   //([y(k-1) y(k)]) just after the zero-crossing accured
extern int tick_count;
extern struct ADdata_t ADdata;
extern int linear_inerpolaion;
extern float frequency;
extern struct ADC_p_p_t ADC_p_p;

float Frequency_with_interpolation(int number_of_samples){
  return 1.0/((number_of_samples*dT)+(-AD_zerocrossing_delay[0]+AD_zerocrossing_delay[1]));
}

float Frequency_without_interpolation(int number_of_samples){
  return 1.0/(dT*number_of_samples);
}




void calculate_frequency(int number_of_samples){
//    if(detect_ZeroCrossing(ADdata.previous_filtered_measurement, ADdata.current_filtered_measurement) == true)
  {
    if(linear_inerpolaion == true){
      //Updating the array with the zero crossing delays 
      AD_zerocrossing_delay[1]=AD_zerocrossing_delay[0];
      AD_zerocrossing_delay[0]=ZeroCrossing_delays(ADdata.previous_filtered_measurement, ADdata.current_filtered_measurement);
      
      frequency = Frequency_with_interpolation(number_of_samples);
    }
    else{
      frequency = Frequency_without_interpolation(number_of_samples);
    }  
  }
}



void calculate_p_p_values(){
    if((detect_ZeroCrossing(ADdata.previous_filtered_measurement, ADdata.current_filtered_measurement) == true)||((tick_count>1000)))
  {
//    ADC_p_p.v_min=(ADC_p_p.v_instance_min_bits-512)*(0.3317803079*2.0); //*(0.4044368601)
//    ADC_p_p.v_max=(ADC_p_p.v_instance_max_bits-512)*(0.3317803079*2.0); //*(0.4044368601)
    
        ADC_p_p.v_min=Bits_2_Grid_Voltage(ADC_p_p.v_instance_min_bits);
    ADC_p_p.v_max=Bits_2_Grid_Voltage(ADC_p_p.v_instance_max_bits);
    
//            ADC_p_p.v_min=(ADC_p_p.v_instance_min_bits);
//    ADC_p_p.v_max=(ADC_p_p.v_instance_max_bits);
    
    ADC_p_p.v_instance_min_bits=(ADC_p_p.v_instance_max_bits-ADC_p_p.v_instance_min_bits)/2;
    ADC_p_p.v_instance_max_bits=(ADC_p_p.v_instance_max_bits-ADC_p_p.v_instance_min_bits)/2; 
  }
}

void update_instance_p_p_values(Int32U current_raw_measurement){
 
    if(current_raw_measurement>ADC_p_p.v_instance_max_bits){
    ADC_p_p.v_instance_max_bits=current_raw_measurement;
  }
  else if(current_raw_measurement<ADC_p_p.v_instance_min_bits){
    
    ADC_p_p.v_instance_min_bits=current_raw_measurement;
  }
  
}
