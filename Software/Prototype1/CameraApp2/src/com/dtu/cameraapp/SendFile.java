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

public class SendFile extends Thread {
	
	File videoFile;
	int sensorID;
	
	public SendFile(File f, int id){
		this.videoFile = f;
		this.sensorID = id;
		
	}
		
	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		super.run();
		try {
			sendAsHttp(videoFile, sensorID);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		
		
		String url = "http://se-se2-e14-glassfish41-c.compute.dtu.dk:8080/Prototype245/rest/video/addVideo?id="+id;
		//String url = "http://10.16.161.162:8080/Ass2/rest/test/addVideo?id="+id;
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		
		// optional default is GET
		con.setRequestMethod("POST");
 
		//add request header
		con.setRequestProperty("Content-Type", "video/mp4");

		
		 
		// Send post request
		con.setDoOutput(true);
		
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.write(bytes);
		wr.close();
		
		return  con.getResponseCode();
	}

}
