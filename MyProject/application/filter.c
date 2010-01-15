#include "filter.h"
#include "config.h"
#include "adc.h"
#include "board.h"

//External structs
extern struct ADdata_t ADdata;

//External variables
extern float alpha;
extern int ADfilter_enable;

//Starting definition of filter functions
//

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
  return ADdata.v_current;
  
}
