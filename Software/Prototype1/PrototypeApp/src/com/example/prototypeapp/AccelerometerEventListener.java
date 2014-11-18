package com.example.prototypeapp;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

public class AccelerometerEventListener implements SensorEventListener {
	
//	private static final int DEFAULT_SENSOR_ID = 1;

//	private TextView textView;
//	private EditText editText;
	private String macAddress;
	
//	private float deltaZ = 0.07f;
	private float deltaX = 5.0f, deltaY = 5.0f, deltaZ = 5.0f;
	
	private float x,y,z, oldX, oldY,oldZ;
	private boolean start = true, thread = false;
	
	public AccelerometerEventListener (String macAddress) {
		super();
//		this.textView = textView;
//		this.editText = editText;
		this.macAddress = macAddress;
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
		
		if (((x - oldX > deltaX) || (oldX - x > deltaX)) && !thread) {
			invokeAddEventWebServer(macAddress, Math.round(x));
		} else if (((y - oldY > deltaY) || (oldY - y > deltaY)) && !thread) {
			invokeAddEventWebServer(macAddress, Math.round(y));
		} else if (((z - oldZ > deltaZ) || (oldZ - z > deltaZ)) && !thread) {
			invokeAddEventWebServer(macAddress, Math.round(z));
		}
		
		oldX=x;
		oldY=y;
		oldZ=z;
		
	}
	
//	private int getSensorId(EditText editText) {
//		if(editText.getText().toString().length() > 0)
//			return Integer.parseInt(editText.getText().toString());
//		return DEFAULT_SENSOR_ID;
//	}

	private void invokeAddEventWebServer(final String macAddress, final int value) {
		new Thread(new Runnable() {			
			@Override
			public void run() {
				thread = true;
				
				try {
					WebServiceConnection.invokeAddEventWebServer(macAddress, value, EventType.ACCELEROMETER);
					Thread.sleep(10000);
//					final String txt = "Event added to server:\n ID: " + result.getId() + " Value: " + result.getValue() + " Time: " + result.getTime();
//					textView.post(new Runnable() {
//						
//						@Override
//						public void run() {						
//							textView.setText(txt);
//							thread = false;
//							
//						}
//					});					
				} catch (Exception e) {
//						textView.post(new Runnable() {
//						
//						@Override
//						public void run() {						
//							textView.setText("Exception");
//							thread = false;
//							
//						}
//					});			
				}
			}
		}).start();
	}
}
