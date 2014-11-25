package com.example.prototypemoniterapp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.datatypes.App;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
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

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		
		private List<AwaitEventThread> awaitEventThreadList = new ArrayList<>();
		private ListView alertList;

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
		public void onViewCreated(View view, Bundle savedInstanceState) {
			super.onViewCreated(view, savedInstanceState);
			
			List<App> apps = WebServiceConnection.invokeGetAppsFromWebServer();
			Set<String> macs = new HashSet<String>();
			
			for (App app : apps) {
				macs.add(app.getMac());
			}
			
			ListView appList = (ListView) getActivity().findViewById(R.id.appList);
			alertList = (ListView) getActivity().findViewById(R.id.alertList);
			
			Button resetButton = (Button) getActivity().findViewById(R.id.resetButton);
			resetButton.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					alertList.removeAllViewsInLayout();
					alertList.setBackgroundColor(Color.WHITE);
				}
			});
			
			for (String mac : macs) {
				TextView textView = new TextView(getActivity());
				textView.setText(mac);
				appList.addView(textView);
				AwaitEventThread awaitEventThread = new AwaitEventThread(alertList, mac, getActivity());
				awaitEventThreadList.add(awaitEventThread);
			}
			
		}
	}
}
