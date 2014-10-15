package com.example.assignment2;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;

public class AccelerometerEventListener implements SensorEventListener {
	
	private FragmentActivity fragmentActivity;
	private TextView txtView;
	
	private float x,y,z, oldX, oldY,oldZ;
	private boolean start = true;
	
	// UPDATE_DELTA
	// SHAKE_DELTA
	
	public AccelerometerEventListener (FragmentActivity fragmentActivity, TextView txtView) {
		super();
		this.fragmentActivity = fragmentActivity;
		this.txtView = txtView;
	}

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onSensorChanged(SensorEvent e) {
		x = e.values[0];
		y = e.values[1];
		z = e.values[2];
		
		if (start) {
			oldX = x;
			oldY = y;
			oldZ = z;
			start = false;
		}
		
		float diff = Math.abs(x+y+z-oldX-oldY-oldZ);
		float value = x+y+z;
		
		// Or TextView.post with runnable()
		txtView.setText(""+value);
		
		if (diff > 10) {
			//txtView.setBackgroundColor(Color.RED);
			Intent intent = new Intent(this.fragmentActivity, ShakeAlertActivity.class);
			intent.putExtra("Value", value);
			this.fragmentActivity.startActivity(intent);
		}
		
		oldX=x;
		oldY=y;
		oldZ=z;
		
	}
	
	

}
