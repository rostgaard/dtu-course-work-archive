struct ADdata_t {
  float v_current;
  float v_previous;
};


void initialize_adc();
//void adc_write();
void adc_start();
void adc_stop();