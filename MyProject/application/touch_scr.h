#include "sys.h"
#include "config.h"

struct Touch_data_t {
  Int32U touched;
  Int32U was_touched;
  Int32U state;
  Int32U accumulator_count;
  Int32U accumulator_size;
  Int32U cursor_stay;
  

  Int32U X_buffer;
  Int32U Y_buffer;
  Int32U X_accumulator;
  Int32U Y_accumulator;
  Int32U X_cursor;
  Int32U Y_cursor;
  Int32U X;
  Int32U Y;
};

typedef struct _Button_t {
  int is_pushed;
  int was_pushed;
  Int32U X_coord; 
  Int32U Y_coord;
  Int32U length;
  Int32U height;
} Button_t, *pButton_t;

extern int screen_state;
extern int screen_state_is_changing;





void TouchScrInit();
void touch_scr_detect_touch(Int32U x);
int touch_scr_touched();
void set_touch_meassure_ch(int ch);
void touch_ADC_setup_Loop();
void switch_touch_meassure_channel();
void update_touch_coordinates();
int check_if_coursor_in_rectangle(int x, int y, int l, int h);

