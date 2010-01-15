#include "zerocrossing.h"
#include "config.h"

float zero_crossing_detection_level = 512.0;

int detect_ZeroCrossing(float v_previous, float v_current) {
  if((v_previous<zero_crossing_detection_level)&(v_current>=zero_crossing_detection_level)) //on rising edge 
    return true;
  else
    return false;
}

float ZeroCrossing_delays(float v_previous, float v_current){
  float T=dT;
  float x_dT=((zero_crossing_detection_level-v_previous)/(v_current-v_previous))*dT;
  return dT-x_dT;
}

void set_zero_crossing_detection_level(float value) {
  zero_crossing_detection_level = value;
}

float get_zero_crossing_detection_level() {
  return zero_crossing_detection_level;
}