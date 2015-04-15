package com.example.prototypemoniterapp;

import java.util.HashMap;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.datatypes.Event;
import com.example.datatypes.EventType;

/**
 * 
 * @author s103459 (Peter), s103470 (Nicolai P)
 *
 */

public class AwaitEventThread extends Thread {
	
	private String macAddress;
	private Activity activity;
	private ListView alertList;
	private ArrayAdapter<String> alertListAdapter;
	private HashMap<String, String> macToDeviceName;
	private boolean run = true;
	
	public AwaitEventThread (String macAddress, Activity activity, ListView view,
							ArrayAdapter<String> adapter, HashMap<String, String> macToDeviceName) {
		this.macAddress = macAddress;
		this.activity = activity;
		this.alertList = view;
		this.alertListAdapter = adapter;
		this.macToDeviceName = macToDeviceName;
	}
	
	
	@Override
	public void run() {
		while (run || !isInterrupted()) {
			
			Event event = null;
			
			try {
				event = WebServiceConnection.invokeAwaitEventWebServer(macAddress, EventType.USERALERT);
			} catch (Exception e) {
				if(!run || isInterrupted()) break; 
			}
			
			if(!run || isInterrupted()) break; 
			
			if (event != null) {
				final String txt = "Alert from device: " + macToDeviceName.get(macAddress) + "\nTime: " + event.getTime();
				
				activity.runOnUiThread(new Runnable() {					
					@Override
					public void run() {
						alertListAdapter.add(txt);
						alertListAdapter.notifyDataSetChanged();
						alertList.setBackgroundColor(Color.rgb(245, 20, 25)); //red
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
		}
	}


	public void terminate() {
		run = false;
		this.interrupt();
	}
}
