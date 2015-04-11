#include "filter.h"
#include "config.h"
#include "adc.h"
#include "board.h"

//External structs
extern struct ADdata_t ADdata;

//External variables
extern float alpha;
extern float alpha_HP;
extern int ADfilter_enable;
extern int HP_filter_enable;

//Starting definition of filter functions
//

void update_alpha() {
    float RC = 1/(2*PI*ADFILTER_CUT_OFF_FREQUENCY);
    float RC_HP = 1/(2*PI*HPFILTER_CUT_OFF_FREQUENCY);
    alpha     = dT/(RC+dT);
    alpha_HP  = RC_HP/(RC_HP+dT);
    
}
float ADfilter(Int32U x){
  
  //shifting data in the ADdata array ([y(k-1) y(k)]) => ADdata(1)=y(k-1), ADdata(0)=y(k)
  //  ADdata.previous_filtered_measurement=ADdata.current_filtered_measurement;
  //  ADdata.current_filtered_measurement=(alpha*x+(1-alpha)*ADdata.previous_filtered_measurement);
 
//  ADdata.previous_mesurement  = ADdata.current_mesurement;
//  ADdata.current_mesurement   = ADdata.current_filtered_measurement ;
  
  if(ADfilter_enable==true){
      ADdata.previous_filtered_measurement=ADdata.current_filtered_measurement;
      ADdata.current_filtered_measurement=(alpha*x+(1-alpha)*ADdata.previous_filtered_measurement);
  }
  
///===============WE SHOULDN'T USE HIGHPASS FILTER - WE WOULD LOOSE THE FREQ. FLUCTUATIONS
////  if(HP_filter_enable==true){
//////      ADdata.current_filtered_measurement=y[i] := alpha * y[i-1] + alpha * (x[i] - x[i-1]);
////      ADdata.HP_previous=ADdata.HP_current;
////      ADdata.HP_current= alpha * ADdata.HP_previous + alpha * (ADdata.current_mesurement - ADdata.previous_mesurement);
////    //===================THATS IT? WHAT DO WE DO WITH ADdata.HP_current???????????
////  }
  else{
      ADdata.previous_filtered_measurement=ADdata.current_filtered_measurement;
      ADdata.current_filtered_measurement=x;
  }
  //return filtered y(k)
  return ADdata.current_filtered_measurement;
  
}
