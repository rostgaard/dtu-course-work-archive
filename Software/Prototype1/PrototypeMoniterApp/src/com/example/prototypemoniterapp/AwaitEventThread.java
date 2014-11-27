package com.example.prototypemoniterapp;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.datatypes.Event;
import com.example.datatypes.EventType;

public class AwaitEventThread extends Thread {
	
	private String macAddress;
	private Activity activity;
	private ListView alertList;
	private ArrayAdapter<String> alertListAdapter;
	private boolean run = true;
	
	public AwaitEventThread (String macAddress, Activity activity, ListView view, ArrayAdapter<String> adapter) {
		this.macAddress = macAddress;
		this.activity = activity;
		this.alertList = view;
		this.alertListAdapter = adapter;
		Log.d("Debug", "Thread created");
	}
	
	
	@Override
	public void run() {
		
		while (run || !isInterrupted()) {
			Log.d("Debug","Start thread");
			
			Event event = null;
			
			try {
				event = WebServiceConnection.invokeAwaitEventWebServer(macAddress, EventType.USERALERT);
//				event = new Event(0,0.0f,System.currentTimeMillis());
			} catch (Exception e) {
				
				if(!run || isInterrupted()) break; 
				
				activity.runOnUiThread(new Runnable() {					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						alertListAdapter.add("Exception: " + macAddress);
						alertListAdapter.notifyDataSetChanged();
					}
				});
			}
			
			if(!run || isInterrupted()) break; 
			
			if (event != null) {
				final String txt = "Alert from device: " + macAddress + "\nTime: " + event.getTime();
				Log.d("Debug","Before add text");
				
				activity.runOnUiThread(new Runnable() {					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						alertListAdapter.add(txt);
						alertListAdapter.notifyDataSetChanged();
						alertList.setBackgroundColor(Color.RED);
					}
				});
				
				NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(activity)
						.setSmallIcon(R.drawable.ic_launcher)
						.setContentTitle("Alarm")
						.setContentText(txt)
						.setAutoCancel(true);
				Intent result = new Intent(activity, MainActivity.class);
				PendingIntent pending = PendingIntent.getActivity(
						activity, 0, result, PendingIntent.FLAG_UPDATE_CURRENT);
				nBuilder.setContentIntent(pending);
				
				NotificationManager nManager = 
						(NotificationManager) activity.getSystemService(Context.NOTIFICATION_SERVICE);
				nManager.notify(0, nBuilder.build());
			}
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


	public void terminate() {
		run = false;
		this.interrupt();
	}
}
