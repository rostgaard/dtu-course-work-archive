package com.example.assignment2;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
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

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		private SensorManager sensorManager;
		private AccelerometerEventListener accelerometerListener;
		
		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
		
		@Override
		public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
			super.onViewCreated(view, savedInstanceState);
			
			if (sensorManager == null)
				sensorManager = (SensorManager) this.getActivity().getSystemService(Context.SENSOR_SERVICE);
			
			
//			if (sensorManager != null && accelerometerListener ==null) {
//				TextView txtView = (TextView) view.findViewById(R.id.textView1);
//				accelerometerListener = new AccelerometerEventListener(this.getActivity(), txtView);
				
//				sensorManager.registerListener(accelerometerListener,
//						sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
//						SensorManager.SENSOR_DELAY_NORMAL);
//			}
			
			
		}

		@Override
		public void onPause() {
			super.onPause();
			sensorManager.unregisterListener(accelerometerListener); //Or on destroy
			accelerometerListener = null;
			
		}

		@Override
		public void onResume() {
			super.onResume();
			TextView txtView = (TextView) getActivity().findViewById(R.id.textView1);
			accelerometerListener = new AccelerometerEventListener(this.getActivity(), txtView);
			
			sensorManager.registerListener(accelerometerListener,
					sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
					SensorManager.SENSOR_DELAY_NORMAL);
			
		}
	}
}
