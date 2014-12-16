package com.example.prototypemoniterapp;

import java.util.List;

import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.widget.VideoView;

import com.example.datatypes.App;
import com.example.datatypes.EventType;

public class VideoThread extends Thread{
	
	private final String url =
			"http://se-se2-e14-glassfish41-c.compute.dtu.dk:8080/Prototype245/rest/video/getVideo?id=";
	private VideoView videoView;
	private String macAddress;
	private boolean running;
	private int oldVideoID;
	private boolean delay;
//	private int counter = 235;

	public VideoThread(VideoView videoView, String macAddress) {
		this.videoView = videoView;
		this.macAddress = macAddress;
		this.running = true;
		this.oldVideoID = -1;
		this.delay = true;
	}

	@Override
	public void run() {
		final Object lock = new Object();
		Log.d("Debug","run");
		List<App> apps = WebServiceConnection.invokeGetAppByMac(macAddress);
		int videoAppID = 0;
		for (App app : apps) {
			if (app.getEventType() == EventType.STARTVIDEORECORDING) {
				videoAppID = app.getId();
			}
		}
		final int finalVideoAppID = videoAppID;
		while(running){
			Log.d("Debug","running");
			final int videoID = WebServiceConnection.invokeGetLatestVideoID(finalVideoAppID);
	        Runnable runnable = new Runnable() {
	            public void run() {
	            	Log.d("Debug","runnable-run");
	            	Log.d("Debug","videoID = " + videoID);
	            	
//	            	int videoID = counter;
	            	if (videoID == oldVideoID) {
	            		Log.d("Debug","if");
	            		if (delay) {
	            			try {
	            				Thread.sleep(1000);
	            				delay = false;
	            			} catch (InterruptedException e) {
	            				// TODO Auto-generated catch block
	            				e.printStackTrace();
	            			}							
						} else {
							running = false;
							Log.d("Debug","running = false");
						}
					} else {
						Log.d("Debug","else");
						delay = true;
						oldVideoID = videoID;
						videoView.setVideoURI(Uri.parse(url + finalVideoAppID + "&count=" + videoID));
						videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
							@Override
							public void onCompletion(MediaPlayer mediaPlayer) {
								Log.d("Debug","onComplete");
								synchronized(lock){lock.notify();}
							}
						});
						Log.d("Debug","videoview start");
						videoView.start();
					}
//	            	counter++;
	            }
	        };
	        VideoActivity.handler.post(runnable);
	        Log.d("Debug","post");
	        synchronized(lock){
	        	try {
	        		Log.d("Debug","wait");
	        		lock.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					Log.d("Debug","exception");
					e.printStackTrace();
				}
	        }
	        videoView.stopPlayback();
		}				
	}
}
