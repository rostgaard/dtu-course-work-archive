package com.example.prototypeapp;

import android.hardware.Camera;
import android.hardware.Camera.Parameters;

public class FlashLightActuator extends Thread {
	
	private Camera camera;
	private Parameters parameters;
	private String macAddress;
	
	public FlashLightActuator (String macAddress) {
		camera = Camera.open();
		parameters = camera.getParameters();
		this.macAddress = macAddress;
	}
	
	@Override
	public void run(){
		Event event = null;
		while (true) {
			try {
				event = WebServiceConnection.invokeAwaitEventWebServer(macAddress, EventType.FLASH_LIGHT);
			} catch (Exception e) {

			}
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
		if (camera != null)	camera.release();
	}

}
