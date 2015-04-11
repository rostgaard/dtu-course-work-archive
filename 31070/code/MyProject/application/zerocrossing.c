#include "zerocrossing.h"
#include "config.h"

float zero_crossing_detection_level = 512.0;

int detect_ZeroCrossing(float previous_filtered_measurement, float current_filtered_measurement) {
  if((previous_filtered_measurement<zero_crossing_detection_level)&(current_filtered_measurement>=zero_crossing_detection_level)) //on rising edge 
    return true;
  else
    return false;
}

float ZeroCrossing_delays(float previous_filtered_measurement, float current_filtered_measurement){
  //float T=dT;
  float x_dT=((zero_crossing_detection_level-previous_filtered_measurement)/(current_filtered_measurement-previous_filtered_measurement))*dT;
  return dT-x_dT;
}

void set_zero_crossing_detection_level(float value) {
  zero_crossing_detection_level = value;
}

float get_zero_crossing_detection_level() {
  return zero_crossing_detection_level;
}