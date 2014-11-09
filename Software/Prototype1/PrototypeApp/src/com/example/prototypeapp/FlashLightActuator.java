package com.example.prototypeapp;

import android.hardware.Camera;
import android.hardware.Camera.Parameters;

public class FlashLightActuator {
	
	private Camera camera;
	private Parameters parameters;
	
	public FlashLightActuator () {
		camera = Camera.open();
		parameters = camera.getParameters();
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
