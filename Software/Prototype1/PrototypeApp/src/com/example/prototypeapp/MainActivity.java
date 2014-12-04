package com.example.prototypeapp;

import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.pm.PackageManager;
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
import android.widget.TextView;

public class MainActivity extends Activity {
	
	public static String macAddress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		macAddress = getMacAddress();
		registerAppsWithServer(macAddress);
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
	
	private void registerAppsWithServer(String mac){
		
		final String macAddress = mac;
		// Registering the apps with the server
		final PackageManager packageManager = this.getPackageManager();
		
		new Thread(new Runnable() {			
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
			
				} catch (Exception e) {
				}
			}
		}).start();
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		
		private SensorManager sensorManager;
		private AccelerometerEventListener accelerometerListener;
		private PlaySoundActuator playSoundActuator;
		private FlashLightActuator flashLightActuator;
		private boolean accelerometerActive = true;
		private boolean playSoundActive = true;
		private boolean flashLightActive = true;

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
			
			setAppStatus();
			TextView status = (TextView) getActivity().findViewById(R.id.status);
			status.setText("Connected");
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
		
		private void setAppStatus() {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					try{
						List<App> apps = WebServiceConnection.invokeGetAppByMac(macAddress);
						for (App app : apps) {
							if (app.getEventType() == EventType.ACCELEROMETER && app.isStatus() == false) {
								accelerometerActive = false;
							}
							if (app.getEventType() == EventType.FLASHLIGHT && app.isStatus() == false) {
								flashLightActive = false;
							}
							if (app.getEventType() == EventType.PLAYSOUND && app.isStatus() == false) {
								playSoundActive = false;
							}
						}
					} catch (Exception e){}
				}
			}).start();
		}
	}
}
