void ADC_Inter_Handler ();
void Timer0IntrHandler (void);
void update_touch_coordinates();
float calculate_X_RMS(float);
float Bits_2_Grid_Voltage(int);

struct ADC_p_p_t{
  float v_instance_max_bits;
  float v_instance_min_bits;  
  float v_max;
  float v_min;
};

