package com.video.assignment6;

import java.io.File;
import java.io.IOException;

import android.content.Context;
import android.hardware.Camera;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.widget.VideoView;

public class RecordingThread extends Thread{

	SurfaceView surfaceView;
    VideoView videoView;
	Context context;
	public RecordingThread(View surfaceView2, VideoView videoView) {
		this.surfaceView = (SurfaceView)surfaceView2;
        this.videoView = videoView;
		this.context = surfaceView2.getContext();
	}

	public SurfaceView getSurfaceView() {
		return surfaceView;
	}

	public void setSurfaceView(SurfaceView surfaceView) {
		this.surfaceView = surfaceView;
	}

	@Override
	@Deprecated
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}
    public void startVideoPlayback(){
        Runnable runnable = new Runnable() {
            public void run() {
                try {
                    videoView.setVideoURI(Uri.parse("http://10.16.174.122:8801"));
                    videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            startVideoPlayback();//Run this function again with the next chunk
                        }
                    });
                    videoView.start();
                   // Log.e("playing file", "file = " + file.toString());
                } catch (RuntimeException e) {
                    Log.e("Internal File Video", "URI not found");
                }
            }
            // }
        };
        videoView.post(runnable);
        videoView.stopPlayback();//Dunno what this does

    }
	@SuppressWarnings("deprecation")
	@Override
	public void run() {
        boolean running = true;
        int count = 1;//Start one video chunk ahead
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
      //  Camera camera = Camera.open();
      //  camera.setDisplayOrientation(90);
      //  MediaRecorder mediaRecorder = new MediaRecorder();
        startVideoPlayback();//This will show an error if there is no video 0, but work fine afterwards
        //  camera.startPreview();
        while(running) {
            //Recording

            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                running = false;

                break;
            }


        }


    }

}
