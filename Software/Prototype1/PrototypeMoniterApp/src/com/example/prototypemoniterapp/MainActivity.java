package com.example.prototypemoniterapp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

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
		private ListView appList;
		private ListView alertList;
		private ArrayAdapter<String> appListAdapter;
		private ArrayAdapter<String> alertListAdapter;

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
			Button clearButton = (Button) getActivity().findViewById(R.id.clearButton);
			clearButton.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					alertListAdapter.clear();
					alertListAdapter.notifyDataSetChanged();
					alertList.setBackgroundColor(Color.TRANSPARENT);
				}
			});
			appList = (ListView) getActivity().findViewById(R.id.appList);
			alertList = (ListView) getActivity().findViewById(R.id.alertList);
//			List<String> list = new ArrayList<String>();
//			list.add("Connecting to server...");
//			appListAdapter = new ArrayAdapter<>(getActivity(),
//					 android.R.layout.simple_list_item_1);
//			appList.setAdapter(appListAdapter);
			setDeviceList();
			
		}
		
		private void setDeviceList() {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					Log.d("Debug", "Start");
					
					List<App> apps;
					try {
						apps = WebServiceConnection.invokeGetAppsFromWebServer();
					} catch (Exception e) {
						apps = new ArrayList<App>();
						Log.d("Debug", "New applist");
						Log.d("Debug", e.toString());
					}
					
					Log.d("Debug", "After apps web get");
					Set<String> macsTemp = new HashSet<String>();
					
					for (App app : apps) {
						macsTemp.add(app.getMac());
					}
					for (int i = 0; i < 3; i++) {
						macsTemp.add("" + i);
					}
					final Set<String> macs = macsTemp;
					Log.d("Debug", "Macs: " + macs);
					
					getActivity().runOnUiThread(new Runnable() {
						
						@Override
						public void run() {
							List<String> arrayList = new ArrayList<String>(macs);
							//appListAdapter.clear();
							appListAdapter = new ArrayAdapter<>(getActivity(),
															 android.R.layout.simple_list_item_1,
															 arrayList);
							appList.setAdapter(appListAdapter);
//							appListAdapter.notifyDataSetChanged();
							
							alertListAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1);
							alertList.setAdapter(alertListAdapter);
							
							
							Log.d("Debug", "Before list add");
							for (String mac : macs) {
								AwaitEventThread awaitEventThread = new AwaitEventThread(mac, getActivity(), alertList, alertListAdapter);
								awaitEventThread.start();
								awaitEventThreadList.add(awaitEventThread);
							}
						}
					});
					
					
				}
			}).start();
		}
	}
}
