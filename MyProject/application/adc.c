#include "adc.h"
#include "board.h"
#include "sys.h"

void initialize_adc()
{
  PINMODE1_bit.P0_25 = 2; 
  PINSEL1_bit.P0_25 = 1;
  
//  PINMODE1_bit.P0_26 = 2; 
//  PINSEL1_bit.P0_26 = 1;
  
  // Init ADC
  PCONP_bit.PCAD = 1;         // Enable ADC clocks
  AD0CR_bit.PDN  = 1;         // converter is operational
  AD0CR_bit.START = 0; 
  AD0CR_bit.SEL  = 31;    // select Ch0-Ch3
  AD0CR_bit.CLKDIV = 3;       //sampling frequency Fs=(AD0CR_bit.CLKDIV)/11=409090Hz
  AD0CR_bit.BURST  = 0;       // disable burst
  AD0CR_bit.CLKS   = 0;       // 10 bits resolution
  ADINTEN_bit.ADINTEN0 = 0;    // select the chanels to generate interupt
  ADINTEN_bit.ADINTEN1 = 0;    // select the chanels to generate interupt
  ADINTEN_bit.ADINTEN2 = 0;    // select the chanels to generate interupt
  ADINTEN_bit.ADINTEN3 = 1;    // select the chanels to generate interupt
  ADINTEN_bit.ADGINTEN = 0;    // select the chanels to generate interupt
  
}


void adc_start(){
  
//    AD0CR_bit.START  = 1;       // enable burst
  
  
  AD0CR_bit.BURST  = 1;       // enable burst


  
}
void adc_stop(){
  
  
//      AD0CR_bit.START  = 0;       // enable burst
  
  
  AD0CR_bit.BURST  = 0;       // disable burst
  

  
}