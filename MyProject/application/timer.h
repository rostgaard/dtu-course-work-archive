void initialize_timer();
void timer0_interrupt_reset();
int GetTimer0Tick();
void update_real_time();

struct time_t {
  int ms_tick_count;
  int second;
  int millisecond;
};