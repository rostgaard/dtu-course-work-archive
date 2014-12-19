package com.dtu.cameraapp;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

public class MainActivity extends Activity {
	
	public static String macAddress;
	private static boolean connected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); //prevents app from crashing on rotation
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                
        macAddress = getMacAddress();
		registerAppsWithServer(macAddress);
		
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
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
		
		new Thread(new Runnable() {			
			@Override
			public void run() {					
				try {
					WebServiceConnection.invokeAddAppToDatabase(macAddress,EventType.STARTVIDEORECORDING);
					connected = true;
				} catch (Exception e) {
					connected = false;
				}
			}
		}).start();		
	}

    

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment implements SurfaceHolder.Callback, OnPreparedListener{
    	
    	RecordingThread recordingThread;
    	
        public PlaceholderFragment() {
        	
        }
        
        
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            View surfaceView = rootView.findViewById(R.id.surfaceView1);
//            TextView statusValue = (TextView) rootView.findViewById(R.id.);
//			if (connected) {
//				statusValue.setText("Connected");
//				statusValue.setTextColor(Color.rgb(0, 150, 0));
//			} else {
//				statusValue.setText("Could not connect to server");
//				statusValue.setTextColor(Color.RED);
//			}
            recordingThread = new RecordingThread(surfaceView, macAddress);
            recordingThread.start();

            return rootView;
		}

        @Override
        public void surfaceCreated(SurfaceHolder holder) {

        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            if (recordingThread != null) {
                recordingThread = null;
            }


        }

        @Override
        public void onPrepared(MediaPlayer mp) {

        }
    }
}
