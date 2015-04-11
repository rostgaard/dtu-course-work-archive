#include "board.h"
#include "sys.h"
#include "timer.h"
#include "config.h"



// This variable holds the status
int measurement_status = NOT_STARTED;

extern struct time_t real_time = {0,0,0};

void initialize_timer() {
  // Enable TIM0 clocks
  PCONP_bit.PCTIM0 = 1; // enable clock


  // Init Timer0
  //timer0_start();
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
  T0TCR_bit.CE = 1;     // counter enable
}

void timer0_interrupt_reset() {
  T0IR_bit.MR0INT = 1;
}

//int GetTimer0Tick() {
//   return T0TC_bit;
//}

void update_real_time(){
    real_time.ms_tick_count++;
  
  if((real_time.ms_tick_count%(TIMER0_TICK_PER_SEC/1000) == 0)) { 
      real_time.millisecond++;
      real_time.ms_tick_count = 0;
      if(real_time.millisecond == 1000) {
        real_time.second++;
        real_time.millisecond=0;
      }
  }  

}

void
timer_set(struct timer *t, clock_time_t interval)
{
  t->interval = interval;
  t->start = clock_time();
}

void
timer_reset(struct timer *t)
{
  t->start += t->interval;
}

void
timer_restart(struct timer *t)
{
  t->start = clock_time();
}

int
timer_expired(struct timer *t)
{
  return (clock_time_t)(clock_time() - t->start) >= (clock_time_t)t->interval;
}