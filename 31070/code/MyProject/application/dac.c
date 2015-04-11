#include "board.h"
void initialize_dac()
{
  
  PINMODE1_bit.P0_26 = 2; 
  PINSEL1_bit.P0_26 = 2;
  
  
}

void dac_write(float value) {
  DACR_bit.VALUE = (Int32U)value;
  
}