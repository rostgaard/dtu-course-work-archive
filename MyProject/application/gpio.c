#include "gpio.h"
#include "board.h"
#include "assert.h"



void initialize_led()
{
  // Init USB Link  LED
  FIO1DIR_bit.P1_18 = 1;
  FIO1DIR_bit.P1_13 = 1;
  
  //Set starting value of led  
  FIO1PIN_bit.P1_18 = 0;
  FIO1PIN_bit.P1_13 = 1;

}

void initialize_relays()
{
  // Init Relay IO ports
  FIO0DIR_bit.P0_11 = 1;
  FIO0DIR_bit.P0_19 = 1;

  //Set starting value of Relay IO ports to 0 
  FIO0PIN_bit.P0_11 = 0;
  FIO0PIN_bit.P0_19 = 0;

}

void toggle_relays(int relay_number, int status) {
  
  assert((status==0)|(status==1));
 
  switch(relay_number) {
  case 1:
    FIO0PIN_bit.P0_11 = status;
    break;
  case 2:
    FIO0PIN_bit.P0_19 = status;
    break;
  default:
    break;
  }
 
 
}

int relays_status(int relay_number) {
  switch(relay_number) {
  case 1:
    return FIO0PIN_bit.P0_11;
  case 2:
    return FIO0PIN_bit.P0_19;
  default:
    return -1;
  }
}


void toggle_led(int relay_number) {
  switch(relay_number) {
  case 1:
    FIO1PIN_bit.P1_18 ^= 1;
    break;
  case 2:
    FIO1PIN_bit.P1_13 ^= 1;
    break;
  default:
    break;
  }
}

int led_status(int led_number) {
  switch(led_number) {
  case 1:
    return FIO1PIN_bit.P1_18;
  case 2:
    return FIO1PIN_bit.P1_13;
  default:
    return -1;
  }
}