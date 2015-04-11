struct ADdata_t {
  
  int current_raw_measurement;
  int previous_raw_measurement;
  int I_current;
  int P_current;

  float current_filtered_measurement;
  float previous_filtered_measurement;
};


void initialize_adc();
//void adc_write();
void adc_start();
void adc_stop();