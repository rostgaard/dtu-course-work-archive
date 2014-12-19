package com.example.prototypeapp;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

/**
 * 
 * @author s103459 (Peter), s103470 (Nicolai P)
 *
 */

public class AccelerometerEventListener implements SensorEventListener {
	
	private String macAddress;
	private float deltaX = 5.0f, deltaY = 5.0f, deltaZ = 5.0f;
	
	private float x,y,z, oldX, oldY,oldZ;
	private boolean start = true, thread = false;
	
	public AccelerometerEventListener (String macAddress) {
		super();
		this.macAddress = macAddress;
	}

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 
	 * @author s103459 (Peter), s103470 (Nicolai P)
	 *
	 */
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
			thread = true;
			invokeAddEventWebServer(macAddress, Math.round(x));
		} else if (((y - oldY > deltaY) || (oldY - y > deltaY)) && !thread) {
			thread = true;
			invokeAddEventWebServer(macAddress, Math.round(y));
		} else if (((z - oldZ > deltaZ) || (oldZ - z > deltaZ)) && !thread) {
			thread = true;
			invokeAddEventWebServer(macAddress, Math.round(z));
		}
		
		oldX=x;
		oldY=y;
		oldZ=z;
		
	}

	/**
	 * 
	 * @author s113444 (Luai)
	 * 
	 */
	private void invokeAddEventWebServer(final String macAddress, final int value) {
		new Thread(new Runnable() {			
			@Override
			public void run() {				
				try {
					WebServiceConnection.invokeAddEventWebServer(macAddress, value, EventType.ACCELEROMETER);
					// wait for 5 seconds, so only one event is sent when a door opens
					Thread.sleep(5000);			
				} catch (Exception e) {	
				}
				thread = false;
			}
		}).start();
	}
}
