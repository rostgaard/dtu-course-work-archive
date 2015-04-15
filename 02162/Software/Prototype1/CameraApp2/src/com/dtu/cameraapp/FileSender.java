package com.dtu.cameraapp;
/**
 * @author Jan-Eric Raab s123673
 * @author Stefan Mertens s113420
 */
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import android.os.AsyncTask;
import android.util.Log;

public class FileSender extends AsyncTask<Object,Void,Integer>{
	
	/**

	* Returns a camera id based on a macaddress and an event type.

	* @param mac	the macaddress of the phone.

	* @param eventType the EventType that the app is registered with, in this case STARTVIDEORECORDING.

	* @return the id corresponding to the phones macaddress and EventType.

	 */
	public static int  sendAsHttp(File file, int id) throws IOException{
		Log.i("Desperate", "FileSender top");
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
		
		String url = "http://se-se2-e14-glassfish41-c.compute.dtu.dk:8080/SmartHomeSecurity/rest/video/addVideo?id="+id;
		 
		Log.w("video", "Sending video to"+url);
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		
		con.setRequestMethod("POST");
 
		//add request header
		con.setRequestProperty("Content-Type", "video/mp4");

		 
		// Send post request
		con.setDoOutput(true);
		
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.write(bytes);
		wr.flush();
		wr.close();
		
		return  con.getResponseCode();
	}

	/* (non-Javadoc)
	 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
	 */
	@Override
	protected void onPostExecute(Integer result) {
		Log.w("thread", "sent file ");
	}

	@Override
	protected Integer doInBackground(Object... params) {
		 try {
			return sendAsHttp((File)params[0], (int) params[1]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

}
