package com.example.prototypemoniterapp;

import java.util.List;

import android.media.MediaPlayer;
import android.net.Uri;
import android.widget.VideoView;

import com.example.datatypes.App;
import com.example.datatypes.EventType;

/**
 * 
 * @author s103459 (Peter), s103470 (Nicolai P)
 *
 */

public class VideoThread extends Thread{
	
	private final String url =
			"http://se-se2-e14-glassfish41-c.compute.dtu.dk:8080/SmartHomeSecurity/rest/video/getVideo?id=";
	private VideoView videoView;
	private String macAddress;
	private boolean running;
	private int oldVideoID;
	private boolean videoDone;

	public VideoThread(VideoView videoView, String macAddress) {
		this.videoView = videoView;
		this.macAddress = macAddress;
		this.running = true;
		this.oldVideoID = -1;
		this.videoDone = false;
	}

	@Override
	public void run() {
		List<App> apps = WebServiceConnection.invokeGetAppByMac(macAddress);
		int videoAppID = 0;
		for (App app : apps) {
			if (app.getEventType() == EventType.STARTVIDEORECORDING) {
				videoAppID = app.getId();
			}
		}
		final int finalVideoAppID = videoAppID;
		while(running){
			final int videoID = WebServiceConnection.invokeGetLatestVideoID(finalVideoAppID);
	        Runnable runnable = new Runnable() {
	            public void run() {	            	
	            	if (videoID == oldVideoID) {
	            		running = false;
					} else {
						oldVideoID = videoID;
						videoView.setVideoURI(Uri.parse(url + finalVideoAppID + "&count=" + videoID));
						videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
							@Override
							public void onCompletion(MediaPlayer mediaPlayer) {
								videoDone = true;
							}
						});
						videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
							
							@Override
							public boolean onError(MediaPlayer mp, int what, int extra) {
								videoDone = true;
								return true;
							}
						});
						videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
							
							@Override
							public void onPrepared(MediaPlayer mp) {
								videoView.start();
							}
						});
					}
	            }
	        };
	        videoDone = false;
	        VideoActivity.handler.post(runnable);
	        while (videoDone == false) {
				try {
					this.sleep(100);
				} catch (InterruptedException e) {
				}
			}
	        videoView.stopPlayback();
		}				
	}
	
	public void terminate() {
		running = false;
		this.interrupt();
	}
}
