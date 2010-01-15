#include "board.h"
#include "sys.h"
#include "touch_scr.h"
#include <assert.h>
#include "adc.h"

struct Touch_data_t Touch_data = {false,X_ch,0,100,false,0,0,0,0,0,0,0,0};
Button_coords_t Up_left_Button = {1, 1, 80, 40};
Button_coords_t Up_right_Button = {240, 1, 80, 40};


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
  if((x>=100)&(Touch_data.touched==false))
  {
    Touch_data.touched=true;
  }
  if((x<=100)&(Touch_data.touched==true))
  {
    Touch_data.touched=false;
  }
}


/*************************************************************************
 * Function Name: set_touch_meassure_ch
 * Parameters: channel for wich we want to set the parameters of transmission
 *
 * Return: none
 *
 * Description: Sets the transmission parameters for the chosen channel
 *
 * Causion: We measure the Y coordinate of the LCD using the X layer and
 * vice-versa
 *
 *************************************************************************/
void set_touch_meassure_ch(int ch)
{
  assert((ch==X_ch)||(ch==Y_ch));
  if(ch==Y_ch)
  {
    //Here we measure the Y coordinate of the LCD using the X layer!!!
      
    //Set Y2=0
    PINMODE1_bit.P0_21  = 2; // neither Pull Up nor Pull Down
    PINSEL1_bit.P0_21   = 0; // GPIO
    FIO0DIR_bit.P0_21   = 1; // output;
    //FIO0SET_bit.P0_21   = 1; // Set pin HIGH
    FIO0CLR_bit.P0_21    = 1; // Set pin LOW
    
    //Set Y1=1
    PINMODE1_bit.P0_23  = 2; // neither Pull Up nor Pull Down
    PINSEL1_bit.P0_23   = 0; // GPIO
    FIO0DIR_bit.P0_23   = 1; // output;
    //FIO0CLR_bit.P0_23    = 1; // Set pin LOW
    FIO0SET_bit.P0_23   = 1; // Set pin HIGH
   
    
    //Set X1=ADC1 Ch1  
    FIO0DIR_bit.P0_24   = 0; // input
    PINMODE1_bit.P0_24  = 2;  // neither Pull Up nor Pull Down
    PINSEL1_bit.P0_24   = 1;  // set as ADC Ch1
      
    //Disable X2  
    FIO0DIR_bit.P0_22   = 0; // input
    PINMODE1_bit.P0_22  = 2; // neither Pull Up nor Pull Down
    PINSEL1_bit.P0_22   = 0; // GPIO
  
  

  }
  else if(ch==X_ch)
  {
    
    //Here we measure the X coordinate of the LCD using the Y layer!!!
    
    //Set X2=0
    PINMODE1_bit.P0_22  = 2; // neither Pull Up nor Pull Down
    PINSEL1_bit.P0_22   = 0; // GPIO
    FIO0DIR_bit.P0_22   = 1; // output;
    FIO0CLR_bit.P0_22    = 1; // Set pin LOW
    
    //Set X1=1
    PINMODE1_bit.P0_24  = 2; // neither Pull Up nor Pull Down
    PINSEL1_bit.P0_24   = 0; // GPIO
    FIO0DIR_bit.P0_24   = 1; // output;
    FIO0SET_bit.P0_24   = 1; // Set pin HIGH
   
    
    //Set Y1=ADC0 Ch0  
    FIO0DIR_bit.P0_23   = 0; // input
    PINMODE1_bit.P0_23  = 2;  // neither Pull Up nor Pull Down
    PINSEL1_bit.P0_23   = 1;  // set as ADC Ch0
      
    //Disable Y2  
    FIO0DIR_bit.P0_21   = 0; // input
    PINMODE1_bit.P0_21  = 2; // neither Pull Up nor Pull Down
    PINSEL1_bit.P0_21   = 0; // GPIO
  }
}


//void touch_ADC_setup_Loop()
//{
//  
//}



/*************************************************************************
 * Function Name: switch_touch_meassure_channel
 * Parameters: none
 *
 * Return: none
 *
 * Description: Changes the meassured channel (Touch_data.state: X->Y or Y->X)
 * and sets the transmission parameters for the new channel
 * (by calling: set_touch_meassure_ch(...))
 *
 *************************************************************************/
void switch_touch_meassure_channel(){
  if(Touch_data.state==X_ch)
    Touch_data.state=Y_ch;
  else if(Touch_data.state==Y_ch)
     Touch_data.state=X_ch;

  if(Touch_data.state==X_ch)
    set_touch_meassure_ch(X_ch); 
  else if(Touch_data.state==Y_ch)
    set_touch_meassure_ch(Y_ch);  
}



int check_if_coursor_in_rectangle(int x, int y, int l, int h){
 
    if(Touch_data.X_cursor>x & Touch_data.X_cursor<(x+l) & Touch_data.Y_cursor>y & Touch_data.Y_cursor<(y+h))
      return 1;
    else
      return 0;
  
}


void update_touch_coordinates(){
  
   if(Touch_data.state==X_ch)
    Touch_data.X_buffer = ADDR0_bit.RESULT;
  
  if(Touch_data.state==Y_ch)
    Touch_data.Y_buffer = ADDR1_bit.RESULT;
  
  // Before change
  
  if(Touch_data.state==Y_ch){
    //Before Calibration
    Touch_data.X = Touch_data.X_buffer; 
    Touch_data.Y = Touch_data.Y_buffer; 
    //After Calibration
//    Touch_data.X = (Int32U)((Touch_data.X_buffer-115.0)*(320.0/(914-115)));
//    Touch_data.Y = (Int32U)(240-(Touch_data.Y_buffer-150.0)*(240.0/(850-150)));
  }   
  
  // After change
  // Cursormode is set in main->Program_Init ()
  if((Touch_data.state==Y_ch)&(Touch_data.accumulator_count<Touch_data.accumulator_size)){
    Touch_data.X_accumulator += Touch_data.X_buffer; 
    Touch_data.Y_accumulator += Touch_data.Y_buffer; 
    Touch_data.accumulator_count++;
  }  
  if((Touch_data.state==Y_ch)&(Touch_data.accumulator_count==Touch_data.accumulator_size)){
    
    Touch_data.X_cursor = (Int32U)(Touch_data.X_accumulator/Touch_data.accumulator_size);
    Touch_data.Y_cursor = (Int32U)(Touch_data.Y_accumulator/Touch_data.accumulator_size);
    Touch_data.X_cursor = (Int32U)((Touch_data.X_cursor-115.0)*(320.0/(914-115)));
    Touch_data.Y_cursor = (Int32U)(240-(Touch_data.Y_cursor-150.0)*(240.0/(850-150)));


    Touch_data.X_accumulator=0;
    Touch_data.Y_accumulator=0;
    Touch_data.accumulator_count=0;
  }
  if(Touch_data.touched==false)
  {
    Touch_data.X_accumulator=0;
    Touch_data.Y_accumulator=0;
    Touch_data.accumulator_count=0;
    if(Touch_data.cursor_stay==false)
    {
      Touch_data.X_cursor=0;
      Touch_data.Y_cursor=0;
    }
  }

}