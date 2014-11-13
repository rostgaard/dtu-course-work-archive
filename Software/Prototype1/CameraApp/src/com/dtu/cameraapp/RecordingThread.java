package com.dtu.cameraapp;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;

import android.content.Context;
import android.hardware.Camera;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.widget.VideoView;


public class RecordingThread extends Thread{

	SurfaceView surfaceView;
	Context context;
	@SuppressWarnings("deprecation")
	Camera camera;
	MediaRecorder mediaRecorder;
	int id = 1;
	//String macAddress;
	
	public RecordingThread(View surfaceView2) {
		this.surfaceView = (SurfaceView)surfaceView2;
		this.context = surfaceView2.getContext();
		//this.macAddress = macAddress;
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
		
        boolean running = true;
        int count = 1;//Start one video chunk ahead
        camera = Camera.open();
        camera.setDisplayOrientation(90);
        mediaRecorder = new MediaRecorder();

        camera.enableShutterSound(false);
        camera.startPreview();
        
        while(running) {
        	
//			event = WebServiceConnection.invokeAwaitEventWebServer(macAddress, EventType.START_VIDEO_RECORDING);
//			
//			if(event != null && event.getEventType().equals(EventType.START_VIDEO_RECORDING)){
			
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
	            mediaRecorder.reset();
	            //byte[] in = Files.readAllBytes(file.toPath());
	            try {
				int result = sendAsHttp(file,id);
				Log.e("debug", "code = "+ result);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
            //Log.w("debug", "Finished. File written: " + file.exists() + " Size: " + file.length());
            count++;
            if (count >= 2){
                count = 0;
            }
//            if(event != null && event.getEventType().equals(EventType.STOP_VIDEO_RECORDING)){
//				running = false;
//				
//			}

//        }

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
	}
	
	public int  sendAsHttp(File file, int id) throws IOException{

		FileInputStream fileInputStream;
		
		byte[] bytes = new byte[(int) file.length()];
		
		try {
        //convert file into array of bytes
	    fileInputStream = new FileInputStream(file);
	    fileInputStream.read(bytes);
	    fileInputStream.close();
 
	    
 
	    System.out.println("Done");
        }catch(Exception e){
        	e.printStackTrace();
        }
		
		String video = Base64.encodeToString(bytes, Base64.DEFAULT);
		
		String url = "http://se-se2-e14-glassfish41-c.compute.dtu.dk:8080/Prototype1/rest/video/addVideo";
		 
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		
		// optional default is GET
		con.setRequestMethod("POST");
 
		//add request header
		con.setRequestProperty("Content-Type", "application/json");
 
		String urlParameters = "{\"id\":\""+id+"\",\"content\":\""+video+"\"}";
		
		 
		// Send post request
		con.setDoOutput(true);
		
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();
		/*
		BufferedReader in = new BufferedReader(
				new java.io.InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		 
		while ((inputLine = in.readLine()) != null) {
			Log.w("Debug", ""+ response.append(inputLine));
		}
		in.close();
		 
		//print result
		System.out.println(response.toString());
		*/		 
		return  con.getResponseCode();
	}
}
