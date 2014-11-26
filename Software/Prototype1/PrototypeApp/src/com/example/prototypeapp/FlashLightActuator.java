package com.example.prototypeapp;

import android.hardware.Camera;
import android.hardware.Camera.Parameters;

public class FlashLightActuator extends Thread {
	
	private Camera camera;
	private Parameters parameters;
	private String macAddress;
	private boolean run = true;
	
	public FlashLightActuator (String macAddress) {
		camera = Camera.open();
		parameters = camera.getParameters();
		this.macAddress = macAddress;
	}
	
	@Override
	public void run(){
		Event event = null;
		while (run || !isInterrupted()) {
			try {
				event = WebServiceConnection.invokeAwaitEventWebServer(macAddress, EventType.FLASHLIGHT);
			} catch (Exception e) {

			}
			if(!run || isInterrupted()) break;
			
			// The flashlight is turned on for five seconds if an event is registered
			if (event != null) {
				on();
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				off();
				event = null;
			}
		}
	}
	
	public void on() {
		parameters.setFlashMode(Parameters.FLASH_MODE_TORCH);
		camera.setParameters(parameters);
		camera.startPreview();
	}
	
	public void off() {
		parameters.setFlashMode(Parameters.FLASH_MODE_OFF);
		camera.setParameters(parameters);
		camera.stopPreview();
	}
	
	public void terminate() {
		run = false;
		if (camera != null)	camera.release();
	}

}
