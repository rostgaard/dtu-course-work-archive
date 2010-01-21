#include <math.h>
#include <stdio.h>

#define DAC_MAXIMUM  1024 // 2^10
#define FREQUENCY 50
#define TIMER0_TICK_PER_SEC 10000    //also the Frequency of the TIMER 0 interupt
#define HTTPD_TICK_PER_SEC 100

#define SAMPLE_FREQUENCY TIMER0_TICK_PER_SEC
#define dT (1.0/SAMPLE_FREQUENCY)

#define VREF 3.3
#define false 0 
#define true 1
#define bulb 1 
#define socket 2
#define X_ch 0
#define Y_ch 1


#define NOT_STARTED 0
#define STARTED 1

#define ADFILTER_CUT_OFF_FREQUENCY 100.0
#define HPFILTER_CUT_OFF_FREQUENCY 10.0

#define PI 3.14159265358979323846


