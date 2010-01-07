
#include <intrinsics.h>
#include <stdio.h>
#include "board.h"
#include "sys.h"
#include "config.h"
#include "sdram_32M_16bit_drv.h"







unsigned long timetick =0;
Int32U Data=50;
int channel = 0;
int adcCLK = 0;
float ADdata[2] = {0.0,0.0};    //([y(k-1) y(k)]) => ADdata(1)=y(k-1), ADdata(0)=y(k)
float alpha;
float frequency;
int tick_count = 0;
int linear_inerpolaion = true;
float zero_crossing_detection_level = 512.0;
float AD_zerocrossing_delay[2] = {0.0,0.0};   //([y(k-1) y(k)]) just after the zero-crossing accured



void start_timer();
void stop_timer();
void initialize_timer();
void ADC_Intr_Handler ();
void Timer0IntrHandler ();
void initialize_led();
void initialize_dac();
void initialize_adc();
void toggle_led(int led_number);
void start_adc();
void update_alpha();
int detect_ZeroCrossing();
float ADfilter(Int32U Data);
float ZeroCrossing_delays();
float Frequency_with_interpolation();
float Frequency_without_interpolation();

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



//void GetTimeTick (void)
//{
//   timetick = T0TC_bit;
//   
//
//}


int main(void)
{
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
 // Init Timer0
  initialize_timer();
  
 //Setup VIC to respond to VIC_TIMER0 and call Timer0IntrHandler()
  VIC_SetVectoredIRQ(Timer0IntrHandler,1,VIC_TIMER0);
  VIC_SetVectoredIRQ(ADC_Intr_Handler,0,VIC_AD0);
  VICINTENABLE |= 1UL << VIC_AD0;  
  VICINTENABLE |= 1UL << VIC_TIMER0;
 // Start Timer
  start_timer();
  start_adc();
 //Enable interrupts
  __enable_interrupt();
  update_alpha(); //sets the alpha value for the low-pass filter 
  while (1) {
    ;
  };
  
}

void initialize_led()
{
  // Init USB Link  LED
  FIO1DIR_bit.P1_18 = 1;
  FIO1DIR_bit.P1_13 = 1;
  
  //Set starting value of led  
  FIO1PIN_bit.P1_18 = 0;
  FIO1PIN_bit.P1_18 = 1;
}

void initialize_dac()
{
  PINMODE1_bit.P0_26 = 2; 
  PINSEL1_bit.P0_26 = 2;
}

void initialize_adc()
{
  PINMODE1_bit.P0_25 = 2; 
  PINSEL1_bit.P0_25 = 1;
  // Init ADC
  PCONP_bit.PCAD = 1;         // Enable ADC clocks
  AD0CR_bit.PDN  = 1;         // converter is operational
  AD0CR_bit.START = 0; 
  AD0CR_bit.SEL |= 1UL << 2;    // select Ch2
  AD0CR_bit.CLKDIV = (SYS_GetFpclk(ADC_PCLK_OFFSET)/ 4);       //sampling frequency Fs=(AD0CR_bit.CLKDIV)/11=409090Hz
  AD0CR_bit.BURST  = 0;       // disable burst
  AD0CR_bit.CLKS   = 0;       // 10 bits resolution
}

void start_adc(){
  AD0CR_bit.START = 1;
}

void initialize_timer()
{
  // Enable TIM0 clocks
  PCONP_bit.PCTIM0 = 1; // enable clock


  // Init Time0
  stop_timer();
  T0TCR_bit.CR = 1;     // set reset
  T0TCR_bit.CR = 0;     // release reset
  T0CTCR_bit.CTM = 0;   // Timer Mode: every rising PCLK edge
  T0MCR_bit.MR0I = 1;   // Enable Interrupt on MR0
  T0MCR_bit.MR0R = 1;   // Enable reset on MR0
  T0MCR_bit.MR0S = 0;   // Disable stop on MR0
  // set timer 0 period
  T0PR = 0;
  T0MR0 = SYS_GetFpclk(TIMER0_PCLK_OFFSET)/(TIMER0_TICK_PER_SEC);
  // init timer 0 interrupt
  T0IR_bit.MR0INT = 1;  // clear pending interrupt
}

void start_timer() {
  T0TCR_bit.CE = 1;     // counting Enable  
}

void stop_timer() {
  T0TCR_bit.CE = 0;     // counting  disable
}

void toggle_led(int led_number) {
  switch(led_number) {
  case 1:
    FIO1PIN ^= (1UL<<18);
    break;
  case 2:
    FIO1PIN ^= (1UL<<13);
    break;
  default:
    break;
  }
}

void ADC_Intr_Handler ()
{
  //Int32U Data;
  
  AD0CR_bit.START = 0;  //Stop the ADC
  
  Data = (Int32U)ADfilter(AD0GDR_bit.RESULT);

  // clear interrupt
  VICADDRESS = 0;
}
void Timer0IntrHandler (void)
{
  AD0CR_bit.START = 1;  //Start the ADC, ADC will be done before next timer interrupt
                        //it has higer priority and will therefore be handlet first  
  tick_count++;
  if(detect_ZeroCrossing() == true)
  {
    if(linear_inerpolaion == true){
      //Updating the array with the zero crossing delays 
      AD_zerocrossing_delay[1]=AD_zerocrossing_delay[0];
      AD_zerocrossing_delay[0]=ZeroCrossing_delays();
      
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
      
    }
  }
  
  if(tick_count < 30)
  { 
    DACR_bit.VALUE = 1023;
  }
  else
  {
    DACR_bit.VALUE = Data;
  }
  //DACR_bit.VALUE = dac_timer;
  //DACR_bit.VALUE = Data;
  // clear interrupt
  T0IR_bit.MR0INT = 1;
  VICADDRESS = 0;
}
void update_alpha()
{
    alpha=dT/(1/(2*PI*ADFILTER_CUT_OFF_FREQUENCY)+dT);
}
float ADfilter(Int32U x){
  
  //shifting data in the ADdata array ([y(k-1) y(k)]) => ADdata(1)=y(k-1), ADdata(0)=y(k)
  ADdata[1]=ADdata[0];
  ADdata[0]=(alpha*x+(1-alpha)*ADdata[1]);
    //return filtered y(k)
    return (Int32U)ADdata[0];
  
}
int detect_ZeroCrossing()
{
  if((ADdata[1]<zero_crossing_detection_level)&(ADdata[0]>=zero_crossing_detection_level)) //on rising edge 
    return true;
  else
    return false;
}

float ZeroCrossing_delays(){

  float x_dT=(zero_crossing_detection_level-ADdata[1])/(ADdata[0]-ADdata[1])*dT;
  return dT-x_dT;

}

float Frequency_with_interpolation(){
  
  return 1.0/((tick_count*dT)+(AD_zerocrossing_delay[0]-AD_zerocrossing_delay[1]));
}

float Frequency_without_interpolation(){

  return 1.0/(dT*tick_count);
}