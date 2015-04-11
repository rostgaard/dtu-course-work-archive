//#include "adc.h"

int detect_ZeroCrossing(float v_previous, float v_current);
float ZeroCrossing_delays(float v_previous, float v_current);

void set_zero_crossing_detection_level(float value);
float get_zero_crossing_detection_level();