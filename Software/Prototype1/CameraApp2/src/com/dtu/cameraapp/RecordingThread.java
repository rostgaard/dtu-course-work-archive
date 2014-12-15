package com.dtu.cameraapp;
/**
 * @author Jan-Eric Raab s123673
 * @author Stefan Mertens s113420
 */
import java.io.File;
import java.io.IOException;

import android.content.Context;
import android.hardware.Camera;
import android.media.MediaRecorder;
import android.media.MediaRecorder.OnInfoListener;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;


public class RecordingThread extends Thread{

	SurfaceView surfaceView;
	Context context;
	
	@SuppressWarnings("deprecation")
	Camera camera;
	MediaRecorder mediaRecorder;
	int id;
	String macAddress;
	
	private static final Object sync = new Object();
	
	public RecordingThread(View surfaceView2, String macAddress) {
		this.surfaceView = (SurfaceView)surfaceView2;
		this.context = surfaceView2.getContext();
		this.macAddress = macAddress;
	}

	public SurfaceView getSurfaceView() {
		return surfaceView;
	}

	public void setSurfaceView(SurfaceView surfaceView) {
		this.surfaceView = surfaceView;
	}

	@Override
	@Deprecated
	//does this the same as terminate?
	public void destroy() {

		super.destroy();
	}

	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		id = WebServiceConnection.invokeGetApp(macAddress, EventType.STARTVIDEORECORDING);
		   
		Log.i("CameraId", "ID = " + id+ "mac = " + macAddress);
        boolean running = true;
        int count = 1;//Start one video chunk ahead
        camera = Camera.open();
        camera.setDisplayOrientation(90);
        mediaRecorder = new MediaRecorder();

        camera.enableShutterSound(false);
        camera.startPreview();
        
        while(running) {
// TODO invoking get id crashes the app when its run the first time, because it takes to long
// for invokeAddAppToDatabase to add the app before it is requested here.
        	
  
	            //Recording
	            setUpMediaRecorder();
	            File file = new File(context.getFilesDir(), "video" + count + ".mpg4");
	           // Log.w("debug", file.getAbsolutePath());
	            String filePath = file.getAbsolutePath();
	            mediaRecorder.setOutputFile(filePath);
	           
	            camera.unlock();
	            try {
	                mediaRecorder.prepare();
	                mediaRecorder.start();
	            } catch (IllegalStateException | IOException e1) {
	                // TODO Auto-generated catch block
	                e1.printStackTrace();
	                //mediaRecorder.stop();
	                camera.stopPreview();
	                running = false;
	                break;
	
	            }
	            try {
	                synchronized (sync){
	                	sync.wait();
	                }
	            } catch (InterruptedException e) {
	                running = false;
	                break;
	            }
	            mediaRecorder.reset();
	            new FileSender().execute(file, id);
			}
            //Log.w("debug", "Finished. File written: " + file.exists() + " Size: " + file.length());
            

        camera.release();

    }
	
	@SuppressWarnings("deprecation")
	private void setUpMediaRecorder(){
		mediaRecorder.setCamera(camera);
        mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
        mediaRecorder.setPreviewDisplay(surfaceView.getHolder().getSurface());
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        mediaRecorder.setVideoSize(camera.getParameters().getPictureSize().width, camera.getParameters().getPictureSize().height);
        mediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);	
        mediaRecorder.setMaxDuration(4000);
        mediaRecorder.setOnInfoListener(new OnInfoListener() {
			
			@Override
			public void onInfo(MediaRecorder mr, int what, int extra) {
				if (what == MediaRecorder.MEDIA_RECORDER_INFO_MAX_DURATION_REACHED) {
					synchronized (sync){
			        mediaRecorder.stop();
					sync.notify();
					}
				}
				
			}
		});
	}
	
	
	
}
