package com.technologyexperimentapp;

import com.technologyexperimentapp.Event.EventType;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.widget.EditText;
import android.widget.TextView;

public class AccelerometerEventListener implements SensorEventListener {
	
	private static final int THRESHOLD = 10;
	private static final int DEFAULT_SENSOR_ID = 1;

	private TextView textView;
	private EditText editText;
	
	private float x,y,z, oldX, oldY,oldZ;
	private boolean start = true, thread = false;
	
	public AccelerometerEventListener (TextView textView, EditText editText) {
		super();
		this.textView = textView;
		this.editText = editText;
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
		//float value = x+y+z;
		
		if (diff > THRESHOLD && !thread) {
			float value = x+y+z;
			invokeAddEventWebServer(getSensorId(editText), value);
		}
		
		oldX=x;
		oldY=y;
		oldZ=z;
		
	}
	
	private int getSensorId(EditText editText) {
		if(editText.getText().toString().length() > 0)
			return Integer.parseInt(editText.getText().toString());
		return DEFAULT_SENSOR_ID;
	}

	private void invokeAddEventWebServer(final int id, final float value) {
		new Thread(new Runnable() {			
			@Override
			public void run() {
				thread = true;
				
				try {
					Event result = WebServiceConnection.invokeAddEventWebServer(id, value, EventType.SHAKE);
					
					final String txt = "Event added to server:\n ID: " + result.getId() + " Value: " + result.getValue() + " Time: " + result.getTime();
					textView.post(new Runnable() {
						
						@Override
						public void run() {						
							textView.setText(txt);
							thread = false;
							
						}
					});					
				} catch (Exception e) {
						textView.post(new Runnable() {
						
						@Override
						public void run() {						
							textView.setText("Exception");
							thread = false;
							
						}
					});			
				}
			}
		}).start();
	}

}
