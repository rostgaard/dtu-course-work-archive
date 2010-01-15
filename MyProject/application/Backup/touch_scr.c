#include "board.h"
#include "sys.h"
#include "touch_scr.h"
#include <assert.h>
#include "adc.h"

struct Touch_data_t Touch_data = {false,wait,wait,0,0,0,0,0,0};


/*************************************************************************
 * Function Name: TouchScrInit
 * Parameters: none
 *
 * Return: none
 *
 * Description: Init Touch screen
 *
 *************************************************************************/
void TouchScrInit()
{
  // Init GPIOs
  TS_X1_SEL   = 1;   // ADC0 Ch1
  TS_X1_MODE  = 2;   // disable pulls
  TS_X2_SEL   = 0;   // general IO
  TS_X2_MODE  = 3;   // enable pull-down

  TS_Y1_SEL   = 0;   // general IO
  TS_Y1_MODE  = 2;   // disable pulls
  TS_Y2_SEL   = 0;   // general IO
  TS_Y2_MODE  = 2;   // disable pulls

  TS_X1_FDIR &= ~TS_X1_MASK;
  TS_X2_FDIR &= ~TS_X2_MASK;
  TS_Y1_FDIR |=  TS_Y1_MASK;
  TS_Y2_FDIR |=  TS_Y2_MASK;

  TS_Y1_FSET  =  TS_Y1_MASK;
  TS_Y2_FSET  =  TS_Y2_MASK;
}


int touch_scr_touched()
{
 return Touch_data.touched;
}

void touch_scr_detect_touch(Int32U x)
{
  // check for touch
  if((x>=512)&(Touch_data.touched==false))
  {
    Touch_data.touched=true;
  }
  if((x<=512)&(Touch_data.touched==true))
  {
    Touch_data.touched=false;
  }
}

void set_touch_meassure_ch(int ch)
{
  assert((ch==X_ch)||(ch==Y_ch)||(ch==wait)||(ch==confirm));
  if(ch==X_ch)
  {
      
    //Set Y1=0
    PINMODE1_bit.P0_23  = 2; // neither Pull Up nor Pull Down
    PINSEL1_bit.P0_23   = 0; // GPIO
    FIO0DIR_bit.P0_23   = 1; // output;
    FIO0CLR_bit.P0_23    = 1; // Set pin LOW
    
    //Set Y2=1
    PINMODE1_bit.P0_21  = 2; // neither Pull Up nor Pull Down
    PINSEL1_bit.P0_21   = 0; // GPIO
    FIO0DIR_bit.P0_21   = 1; // output;
    FIO0SET_bit.P0_21   = 1; // Set pin HIGH
   
    
    //Set X1=ADC1 Ch0  
    FIO0DIR_bit.P0_24   = 0; // input
    PINMODE1_bit.P0_24  = 2;  // neither Pull Up nor Pull Down
    PINSEL1_bit.P0_24   = 1;
      
    //Disable X2  
    FIO0DIR_bit.P0_22   = 0; // input
    PINMODE1_bit.P0_22  = 2; // neither Pull Up nor Pull Down
    PINSEL1_bit.P0_22   = 0; // GPIO
  
  

  }
  else if(ch==Y_ch)
  {
    //Set X1=0
    PINMODE1_bit.P0_24  = 2; // neither Pull Up nor Pull Down
    PINSEL1_bit.P0_24   = 0; // GPIO
    FIO0DIR_bit.P0_24   = 1; // output;
    FIO0CLR_bit.P0_24    = 1; // Set pin LOW
    
    //Set X2=1
    PINMODE1_bit.P0_22  = 2; // neither Pull Up nor Pull Down
    PINSEL1_bit.P0_22   = 0; // GPIO
    FIO0DIR_bit.P0_22   = 1; // output;
    FIO0SET_bit.P0_22   = 1; // Set pin HIGH
   
    
    //Set Y1=ADC0 Ch0  
    FIO0DIR_bit.P0_23   = 0; // input
    PINMODE1_bit.P0_23  = 2;  // neither Pull Up nor Pull Down
    PINSEL1_bit.P0_23   = 1;
      
    //Disable Y2  
    FIO0DIR_bit.P0_21   = 0; // input
    PINMODE1_bit.P0_21  = 2; // neither Pull Up nor Pull Down
    PINSEL1_bit.P0_21   = 0; // GPIO
  }
  else if(ch==wait)
  {  
////////    // Init GPIOs
////////    //X1
////////    PINSEL1_bit.P0_24   = 1;   // ADC0 Ch1
////////    PINMODE1_bit.P0_24  = 2;   // disable pulls
////////    FIO0DIR_bit.P0_24   = 0;   // input;
////////
////////    
////////    //X2
////////    PINSEL1_bit.P0_22   = 0;   // general IO
////////    PINMODE1_bit.P0_22  = 3;   // pulls down
////////    FIO0DIR_bit.P0_22   = 0;   // input;
////////    
////////    //Y1
////////    PINSEL1_bit.P0_23   = 0;   // general IO
////////    //PINMODE1_bit.P0_23  = 3;   // enable pull-down
////////    FIO0DIR_bit.P0_22   = 1;  // output;
////////    FIO0SET_bit.P0_22   = 1;  // Set pin HIGH
////////   
////////    
////////    //Y2
////////    PINSEL1_bit.P0_21   = 0;   // general IO
////////    //PINMODE1_bit.P0_21  = 3;   // enable pull-down
////////    FIO0DIR_bit.P0_22   = 1;   // output;
////////    FIO0SET_bit.P0_22   = 1;   // Set pin HIGH
        //Set Y1=1
    PINMODE1_bit.P0_23  = 2; // neither Pull Up nor Pull Down
    PINSEL1_bit.P0_23   = 0; // GPIO
    FIO0DIR_bit.P0_23   = 1; // output;
    FIO0SET_bit.P0_23    = 1; // Set pin LOW
    
    //Set Y2=1
    PINMODE1_bit.P0_21  = 2; // neither Pull Up nor Pull Down
    PINSEL1_bit.P0_21   = 0; // GPIO
    FIO0DIR_bit.P0_21   = 1; // output;
    FIO0SET_bit.P0_21   = 1; // Set pin HIGH
   
    
    //Set X1=ADC1 Ch0  
    FIO0DIR_bit.P0_24   = 0; // input
    PINMODE1_bit.P0_24  = 2;  // neither Pull Up nor Pull Down
    PINSEL1_bit.P0_24   = 1;
      
    //Disable X2  
    FIO0DIR_bit.P0_22   = 0; // input
    PINMODE1_bit.P0_22  = 2; // neither Pull Up nor Pull Down
    PINSEL1_bit.P0_22   = 0; // GPIO
    
  }
  if(ch==confirm)
  {
        //Set Y1=1
    PINMODE1_bit.P0_23  = 2; // neither Pull Up nor Pull Down
    PINSEL1_bit.P0_23   = 0; // GPIO
    FIO0DIR_bit.P0_23   = 1; // output;
    FIO0SET_bit.P0_23   = 1; // Set pin LOW
    
    //Set Y2=1
    PINMODE1_bit.P0_21  = 2; // neither Pull Up nor Pull Down
    PINSEL1_bit.P0_21   = 0; // GPIO
    FIO0DIR_bit.P0_21   = 1; // output;
    FIO0SET_bit.P0_21   = 1; // Set pin HIGH
   
    
    //Set X1=ADC1 Ch0  
    FIO0DIR_bit.P0_24   = 0; // input
    PINMODE1_bit.P0_24  = 2;  // neither Pull Up nor Pull Down
    PINSEL1_bit.P0_24   = 1;
      
    //Disable X2  
    PINSEL1_bit.P0_22   = 0; // GPIO
    FIO0DIR_bit.P0_22   = 1; // input
    PINMODE1_bit.P0_22  = 3; // neither Pull Up nor Pull Down
    
    
////////    // Init GPIOs
////////    //X1
////////    PINSEL1_bit.P0_24   = 1;   // ADC0 Ch1
////////    PINMODE1_bit.P0_24  = 2;   // disable pulls
////////    FIO0DIR_bit.P0_24   = 0;   // input;
////////
////////    
////////    //X2
////////    PINSEL1_bit.P0_22   = 0;   // general IO
////////    PINMODE1_bit.P0_22  = 3;   // pulls down
////////    FIO0DIR_bit.P0_22   = 0;   // input;
////////    
////////    //Y1
////////    PINSEL1_bit.P0_23   = 0;   // general IO
////////    FIO0DIR_bit.P0_22   = 1;  // output;
////////    FIO0SET_bit.P0_22   = 1;  // Set pin HIGH
////////   
////////    
////////    //Y2
////////    PINSEL1_bit.P0_21   = 0;   // general IO
////////    FIO0DIR_bit.P0_22   = 1;   // output;
////////    FIO0SET_bit.P0_22   = 1;   // Set pin HIGH
    
  }
}


void touch_ADC_setup_Loop()
{
  
    if(Touch_data.touched)
    {
      
      switch(Touch_data.state)
      {
        case wait:
//          touch_scr_detect_touch(Touch_data.X_buffer);
          
          set_touch_meassure_ch(X_ch);
          Touch_data.last_state=Touch_data.state;
          Touch_data.state++;
          break;
        case X_ch:
          Touch_data.X_temp = Touch_data.X_buffer;
          set_touch_meassure_ch(Y_ch);
      
          Touch_data.last_state=Touch_data.state;
          Touch_data.state++;
          break;
        case Y_ch:
          Touch_data.Y_temp =  Touch_data.Y_buffer;
          set_touch_meassure_ch(confirm);
      
          Touch_data.last_state=Touch_data.state;
          Touch_data.state++;
          break;
        case confirm:
          touch_scr_detect_touch(Touch_data.X_buffer);
          
          if(Touch_data.last_state == confirm){
            Touch_data.X = Touch_data.X_temp;
            Touch_data.Y = Touch_data.Y_temp;
            Touch_data.state = wait ;     //reset state
            set_touch_meassure_ch(wait);  //measure if screen is touched
            break;
          }

          set_touch_meassure_ch(confirm);
          Touch_data.last_state=Touch_data.state;

//          Touch_data.last_state=Touch_data.state;
//          set_touch_meassure_ch(wait);
          break;
        default:
          break;
      }
    }
    else
    {touch_scr_detect_touch(Touch_data.X_buffer);}
  
}