package com.example.prototypeapp;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

public class MainActivity extends Activity {
	
	public static final String FRAGMENT_ARGUMENT = "mac";
	public static String macAddress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
//		macAddress = getMacAddress();
		macAddress = "macc";
		registerAppsWithServer(macAddress);
		if (savedInstanceState == null) {
//			Bundle bundle = new Bundle();
//			bundle.putString(FRAGMENT_ARGUMENT, macAddress);
//			PlaceholderFragment placeholderFragment = new PlaceholderFragment();
//			placeholderFragment.setArguments(bundle);
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
						WebServiceConnection.invokeAddAppToDatabase(macAddress,EventType.FLASH_LIGHT);
					}
					// We assume the device has a speaker (we cannot check it)
					WebServiceConnection.invokeAddAppToDatabase(macAddress,EventType.PLAY_SOUND);
			
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
//		private String macAddress;

		public PlaceholderFragment() {
			Log.d("ourOwnDebug", "before");
//			this.macAddress = this.getArguments().getString(FRAGMENT_ARGUMENT);
//			Log.d("ourOwnDebug", this.getArguments().getString(FRAGMENT_ARGUMENT));
//			this.macAddress = "mac5";
		}

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
		}
		
		@Override
		public void onResume() {
			super.onResume();
			
			accelerometerListener = new AccelerometerEventListener(macAddress);
			
			sensorManager.registerListener(accelerometerListener,
					sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION),
					SensorManager.SENSOR_DELAY_NORMAL);
			
			playSoundActuator = new PlaySoundActuator(getActivity(), macAddress);
			playSoundActuator.start();
			
			flashLightActuator = new FlashLightActuator(macAddress);
			flashLightActuator.start();
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
	}
}
