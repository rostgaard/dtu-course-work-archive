package com.example.prototypemoniterapp;

import android.app.Activity;
import android.graphics.Color;
import android.widget.ListView;
import android.widget.TextView;

import com.example.datatypes.Event;
import com.example.datatypes.EventType;

public class AwaitEventThread extends Thread {
	
	private ListView alertList;
	private String macAddress;
	private Activity activity;
	private boolean run = true;
	
	public AwaitEventThread (ListView alertList, String macAddress, Activity activity) {
		this.alertList = alertList;
		this.macAddress = macAddress;
		this.activity = activity;
	}
	
	
	@Override
	public void run() {
		
		while (run || !isInterrupted()) {
			
			Event event = null;
			
			try {
				event = WebServiceConnection.invokeAwaitEventWebServer(macAddress, EventType.ACCELEROMETER);
			} catch (Exception e) {
				
				if(!run || isInterrupted()) break; 
				
				alertList.post(new Runnable() {
					
					@Override
					public void run() {						
						TextView textView = new TextView(activity);
						textView.setText("Exception");
						alertList.addView(textView);
					}
				});
			}
			
			if(!run || isInterrupted()) break; 
			
			if (event != null) {
				final String txt = "Alert from device: " + macAddress + "\nTime: " + event.getTime();
				alertList.post(new Runnable() {
					@Override
					public void run() {
						TextView textView = new TextView(activity);
						textView.setText(txt);
						alertList.addView(textView);
						alertList.setBackgroundColor(Color.RED);
					}
				});
			}
		}
	}


	public void terminate() {
		run = false;
		this.interrupt();
	}
}
