package com.example.prototypemoniterapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.datatypes.App;
import com.example.datatypes.Device;
import com.example.datatypes.EventType;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.main, menu);
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
		private Map<String,HashSet<EventType>> availableApps;
		private boolean connected = false;
		private ArrayList<String> arrayList;
		private HashMap<String, String> macToDeviceName;

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
			appList.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					String mac = arrayList.get(position);
					
					HashSet<EventType> availableAppsAtMac = availableApps.get(mac);
					String[] choices = new String[availableAppsAtMac.size()];
					int index = 0;
					for (Iterator<EventType> iterator = availableAppsAtMac.iterator(); iterator
							.hasNext();) {
						EventType eventType = (EventType) iterator.next();
						switch (eventType) {
						case FLASHLIGHT:
							choices[index] = "Activate Flashlight";
							break;
						case PLAYSOUND:
							choices[index] = "Activate Sound";
							break;
						case STARTVIDEORECORDING:
							choices[index] = "Get Video Feed";
							break;
						default:
							break;
						}
						index++;
					}
					if (connected) {
						ManageDeviceDialog dialog = ManageDeviceDialog.newInstance(choices, mac);
						dialog.show(getFragmentManager(), "dialog");
					}
				}
			});
			alertList = (ListView) getActivity().findViewById(R.id.alertList);
			setDeviceList();
		}
		
		private void setDeviceList() {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					
					List<App> apps;
					try {
						apps = WebServiceConnection.invokeGetAppsFromWebServer();
						connected = true;
					} catch (Exception e) {
						apps = new ArrayList<App>();
					}
					final List<App> finalApps = apps;
					
					Set<String> macs = new HashSet<String>();
					
					for (App app : apps) {
						macs.add(app.getMac());
					}
					macToDeviceName = new HashMap<>();
					for (String mac : macs) {
						String name;
						try {
							Device device = WebServiceConnection.invokeGetDeviceByMac(mac);							
							name = device.getName();
						} catch (Exception e) {
							Log.d("Debug","getDeviceByMac exception");
							name = "";
						}
						if (name == null || name.equals("")) {
							macToDeviceName.put(mac, mac);
						} else {
							macToDeviceName.put(mac, name);
						}
					}
					
					final Set<String> finalMacs = macs;
					
					getActivity().runOnUiThread(new Runnable() {
						
						@Override
						public void run() {
							ArrayList<String> deviceNameList = new ArrayList<>();
							if (connected) {
								arrayList = new ArrayList<String>(finalMacs);
								for (String mac : arrayList) {
									deviceNameList.add(macToDeviceName.get(mac));
								}
							} else {
								deviceNameList.add("Could not connect to server");
							}
							appListAdapter = new ArrayAdapter<>(getActivity(),
															 android.R.layout.simple_list_item_1,
															 deviceNameList);
							appList.setAdapter(appListAdapter);
							
							alertListAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1);
							alertList.setAdapter(alertListAdapter);
							
							for (String mac : finalMacs) {
								AwaitEventThread awaitEventThread = new AwaitEventThread(mac, getActivity(), alertList, alertListAdapter);
								awaitEventThread.start();
								awaitEventThreadList.add(awaitEventThread);
							}
							availableApps = new HashMap<>();
							for (App app : finalApps) {
								String mac = app.getMac();
								EventType type = app.getEventType();
								if (app.isStatus() && (type == EventType.FLASHLIGHT || type == EventType.PLAYSOUND
														|| type == EventType.STARTVIDEORECORDING)) {
									if (availableApps.containsKey(mac)) {
										availableApps.get(mac).add(type);
									} else {
										HashSet<EventType> newSet = new HashSet<EventType>();
										newSet.add(type);
										availableApps.put(mac, newSet);
									}									
								}
							}
						}
					});
				}
			}).start();
		}
	}
}
