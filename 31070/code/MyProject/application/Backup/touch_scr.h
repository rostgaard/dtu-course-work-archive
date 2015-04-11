#include "sys.h"
#include "config.h"

struct Touch_data_t {
  Int32U touched;
  Int32U last_state;
  Int32U state;

  Int32U X_buffer;
  Int32U Y_buffer;
  Int32U X_temp;
  Int32U Y_temp;
  Int32U X;
  Int32U Y;
};



void TouchScrInit();
void touch_scr_detect_touch(Int32U x);
int touch_scr_touched();
void set_touch_meassure_ch(int ch);
void touch_ADC_setup_Loop();
