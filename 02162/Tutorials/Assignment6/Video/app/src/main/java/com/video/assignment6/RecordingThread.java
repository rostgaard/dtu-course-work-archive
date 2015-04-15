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
    final Object lock = new Object();
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
    public void startVideoPlayback(int i){
        final int count = i;
        Runnable runnable = new Runnable() {
            public void run() {
                File file = new File(context.getFilesDir(), "video" + count + ".mpg4");
                try {
                    videoView.setVideoURI(Uri.fromFile(file));
                    videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            int x = count;//need a new variable because count is final
                            x++;
                            if (x >= 2) x = 0;
                            synchronized (lock){
                                try {
                                    lock.wait();//Wait for the recording thread to save a new chunk
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            startVideoPlayback(x);//Run this function again with the next chunk
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
        Camera camera = Camera.open();
        camera.setDisplayOrientation(90);
        MediaRecorder mediaRecorder = new MediaRecorder();
        startVideoPlayback(0);//This will show an error if there is no video 0, but work fine afterwards
        camera.enableShutterSound(false);
        camera.startPreview();
        while(running) {
            //Recording
            mediaRecorder.setCamera(camera);
            mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
            mediaRecorder.setPreviewDisplay(surfaceView.getHolder().getSurface());
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);

            mediaRecorder.setVideoSize(camera.getParameters().getPictureSize().width, camera.getParameters().getPictureSize().height);

            mediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.DEFAULT);
            File file = new File(context.getFilesDir(), "video" + count + ".mpg4");
           // Log.w("debug", file.getAbsolutePath());
            String filePath = file.getAbsolutePath();
            mediaRecorder.setOutputFile(filePath);
            camera.unlock();
            try {
                mediaRecorder.setOrientationHint(90);
                mediaRecorder.prepare();
                mediaRecorder.start();
              //  Log.w("debug", "MediaRecorder started");
            } catch (IllegalStateException | IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                //mediaRecorder.stop();
                camera.stopPreview();
                running = false;
                break;

            }
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                running = false;
                mediaRecorder.stop();
                camera.stopPreview();
                break;
            }

           // Log.w("debug", "Mediarecorder stopping");
            mediaRecorder.stop();
            synchronized (lock){lock.notifyAll();}//New chunk saved, notify player
            mediaRecorder.reset();
           // camera.stopPreview();

            //Log.w("debug", "Finished. File written: " + file.exists() + " Size: " + file.length());
            count++;
            if (count >= 2){
                count = 0;
            }

        }

        camera.release();

    }

}
