package com.video.assignment6;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

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
	Context context;
	public RecordingThread(View surfaceView2) {
		this.surfaceView = (SurfaceView)surfaceView2;
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

		super.destroy();
	}

	@SuppressWarnings("deprecation")
	@Override
	public void run() {
        boolean running = true;
        int count = 1;//Start one video chunk ahead
        Camera camera = Camera.open();
        camera.setDisplayOrientation(90);
        MediaRecorder mediaRecorder = new MediaRecorder();

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
           sendFile(file);

            //Log.w("debug", "Finished. File written: " + file.exists() + " Size: " + file.length());
            count++;
            if (count >= 2){
                count = 0;
            }

        }

        camera.release();

    }

    private void sendFile(File file) {
        Socket socket = null;
        FileInputStream is = null;
        try {
            socket = new Socket("10.16.174.122", 8800);
        } catch (IOException e) {
            e.printStackTrace();
        }

        OutputStream outputStream = null;
        try {
            outputStream = socket.getOutputStream();
             is = new FileInputStream(file);

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.close();
            outputStream = null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    System.out.println("IOException occurred");
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    System.out.println("IOException occurred");
                }
            }
        }
    }

}
