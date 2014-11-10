package com.example.prototypeapp;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.widget.EditText;
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
		private AwaitEventThread awaitEventThread;
		private PlaySoundActuator playSoundActuator;
		
//		public static final int S1 = R.raw.wopwop;
//		private static SoundPool soundPool;

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
		}
		
		@Override
		public void onResume() {
			super.onResume();
			
			EditText editText = (EditText)getActivity().findViewById(R.id.editText1);
			TextView textView = (TextView)  getActivity().findViewById(R.id.textView1);	
			accelerometerListener = new AccelerometerEventListener(textView, editText);
			
			sensorManager.registerListener(accelerometerListener,
					sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION),
					SensorManager.SENSOR_DELAY_NORMAL);
			
			playSoundActuator = new PlaySoundActuator(getActivity(), 1337);
			playSoundActuator.start();
			
			awaitEvent();
		}
		
		@Override
		public void onDestroy() {
			super.onDestroy();
			playSoundActuator.terminate();
			playSoundActuator = null;
//			sensorManager.unregisterListener(accelerometerListener);
//			accelerometerListener = null;
		}
		
		private synchronized void awaitEvent() {
			
			if (awaitEventThread != null) {
				awaitEventThread.terminate();
				awaitEventThread = null;
			}
			
			final EditText editText = (EditText) getActivity().findViewById(R.id.editText2);
			final TextView textView = (TextView) getActivity().findViewById(R.id.textView2);
			awaitEventThread = new AwaitEventThread(editText, textView);
			
			if (!textView.getText().equals(""))
				awaitEventThread.start();
			
			editText.setOnFocusChangeListener(new OnFocusChangeListener() {
				
				@Override
				public void onFocusChange(View v, boolean hasFocus) {
					if (!hasFocus) {
						textView.setText("Current Sensor ID: " + editText.getText());
						awaitEvent();
					}
				}
			});
			
			editText.setOnKeyListener(new OnKeyListener() {
				
				@Override
				public boolean onKey(View v, int keyCode, KeyEvent event) {
					if (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
						textView.setText("Current Sensor ID: " + editText.getText());
						awaitEvent();
					}
					return false;
				}
			});
			
		}
		
		
		

	}
}