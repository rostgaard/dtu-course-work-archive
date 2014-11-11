package com.example.prototypeapp;

import android.widget.TextView;

public class AwaitEventThread extends Thread {
	
//	private EditText editText;
	private TextView textView;
	private String macAddress;
	private boolean run = true;
	
	public AwaitEventThread (TextView textView, String macAddress) {
//		this.editText = editText;
		this.textView = textView;
		this.macAddress = macAddress;
	}
	
	

	@Override
	public void run() {
		
		int count = 0;
		
		while (run || !isInterrupted()) {
			
			Event event = null;
			
			try {
//				int id = Integer.parseInt(editText.getText().toString());
				event = WebServiceConnection.invokeAwaitEventWebServer(macAddress, EventType.ACCELEROMETER);
			} catch (Exception e) {
				
				if(!run || isInterrupted()) break; 
				
				textView.post(new Runnable() {
					
					@Override
					public void run() {						
						textView.setText("Exception");
					}
				});
			}
			
			if(!run || isInterrupted()) break; 
			
			if (event != null) {
				final String txt = "Await Event:\n ID: " + event.getId() + " Value: " + event.getValue() + " Time: " + event.getTime();
				textView.post(new Runnable() {
					@Override
					public void run() {						
						textView.setText(txt);
					}
				});
			} else {
				count++;
				final String countTxt = "" + count; 
				textView.post(new Runnable() {
					
					@Override
					public void run() {						
						textView.setText("Timeout " + countTxt);
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
