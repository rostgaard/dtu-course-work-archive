package com.example.prototypeapp;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * 
 * @author s103459 (Peter), s103470 (Nicolai P)
 *
 */

public class MainActivity extends Activity {
	
	public static String macAddress;
	private static boolean connected = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		macAddress = getMacAddress();
		Thread registerAppsThread = registerAppsWithServer(macAddress);
		registerAppsThread.start();
		try {
			registerAppsThread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private String getMacAddress() {
		WifiManager manager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
		WifiInfo info = manager.getConnectionInfo();
		return info.getMacAddress();
	}
	
	private Thread registerAppsWithServer(String mac){
		
		final String macAddress = mac;
		// Registering the apps with the server
		final PackageManager packageManager = this.getPackageManager();
		
		return new Thread(new Runnable() {			
			@Override
			public void run() {					
				try {
					// Check for accelerometer
					if (packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_ACCELEROMETER)){
						WebServiceConnection.invokeAddAppToDatabase(macAddress,EventType.ACCELEROMETER);
					}
					// Check for flashlight
					if (packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)){
						WebServiceConnection.invokeAddAppToDatabase(macAddress,EventType.FLASHLIGHT);
					}
					// We assume the device has a speaker (we cannot check it)
					WebServiceConnection.invokeAddAppToDatabase(macAddress,EventType.PLAYSOUND);
					
					WebServiceConnection.invokeAddAppToDatabase(macAddress,EventType.USERALERT);
					connected = true;
			
				} catch (Exception e) {
					connected = false;
				}
			}
		});
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		
		private SensorManager sensorManager;
		private AccelerometerEventListener accelerometerListener;
		private PlaySoundActuator playSoundActuator;
		private FlashLightActuator flashLightActuator;
		private boolean accelerometerActive = false;
		private boolean playSoundActive = false;
		private boolean flashLightActive = false;
		private ArrayList<String> activeApps;

		public PlaceholderFragment() {}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);			
			return rootView;
		}
		@Override
		public void onViewCreated(View view, Bundle savedInstanceState) {
			super.onViewCreated(view, savedInstanceState);
			
			if (sensorManager == null)
				sensorManager = (SensorManager) this.getActivity().getSystemService(Context.SENSOR_SERVICE);
			
			Thread setStatusThread = setAppStatus();
			setStatusThread.start();
			try {
				setStatusThread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			TextView statusValue = (TextView) getActivity().findViewById(R.id.status);
			if (connected) {
				statusValue.setText("Connected with MAC address: " + macAddress);
				statusValue.setTextColor(Color.rgb(0, 150, 0));
			} else {
				statusValue.setText("Could not connect to server - Restart app");
				statusValue.setTextColor(Color.RED);
			}
		}
		
		@Override
		public void onResume() {
			super.onResume();
			
			if (accelerometerActive) {
				accelerometerListener = new AccelerometerEventListener(macAddress);
				
				sensorManager.registerListener(accelerometerListener,
						sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION),
						SensorManager.SENSOR_DELAY_NORMAL);
			}		
			
			if (playSoundActive) {
				playSoundActuator = new PlaySoundActuator(getActivity(), macAddress);
				playSoundActuator.start();
			}
			
			if (flashLightActive) {
				flashLightActuator = new FlashLightActuator(macAddress);
				flashLightActuator.start();
			}
		}
		
		@Override
		public void onDestroy() {
			super.onDestroy();
			playSoundActuator.terminate();
			playSoundActuator = null;
			flashLightActuator.terminate();
			flashLightActuator = null;
			sensorManager.unregisterListener(accelerometerListener);
			accelerometerListener = null;
		}
		
		private Thread setAppStatus() {
			return new Thread(new Runnable() {
				
				@Override
				public void run() {
					try{
						activeApps = new ArrayList<String>();
						List<App> apps = WebServiceConnection.invokeGetAppByMac(macAddress);
						for (App app : apps) {
							if (app.getEventType() == EventType.ACCELEROMETER && app.isStatus() == true) {
								accelerometerActive = true;
								activeApps.add("Door Sensor");
							}
							if (app.getEventType() == EventType.FLASHLIGHT && app.isStatus() == true) {
								flashLightActive = true;
								activeApps.add("Flashlight");
							}
							if (app.getEventType() == EventType.PLAYSOUND && app.isStatus() == true) {
								playSoundActive = true;
								activeApps.add("Sound");
							}
						}
						ArrayAdapter<String> listAdapter = new ArrayAdapter<>(getActivity(),
															android.R.layout.simple_list_item_1,
															activeApps);
						ListView activeAppsList = (ListView) getActivity().findViewById(R.id.active_apps);
						activeAppsList.setAdapter(listAdapter);
					} catch (Exception e){}
				}
			});
		}
	}
}
