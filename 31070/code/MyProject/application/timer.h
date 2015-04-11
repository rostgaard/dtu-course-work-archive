#include "clock.h"

void initialize_timer();
void timer0_interrupt_reset();
int GetTimer0Tick();
void update_real_time();

struct time_t {
  int ms_tick_count;
  int second;
  int millisecond;
};

struct timer {
  clock_time_t start;
  clock_time_t interval;
};

void timer_set(struct timer *t, clock_time_t interval);
void timer_reset(struct timer *t);
void timer_restart(struct timer *t);
int timer_expired(struct timer *t);