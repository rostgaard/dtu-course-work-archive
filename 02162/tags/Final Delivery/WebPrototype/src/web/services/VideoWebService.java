package web.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import web.services.util.VOD;

/**
<<<<<<< .mine
 * Video web service with endpoints for video upload and playback
 * @author stefan, s113420
=======
 * 
 * @author s113420 (Stefan)
>>>>>>> .r398
 *
 */

@LocalBean
@Stateless
@Path("/video")
public class VideoWebService {

	/**
	 * Endpoint to store a video on the server
	 * @param id The device ID for which to store the video, camera app will just send its own ID, theoretically anything can be put here, the server will
	 * just create a corresponding folder. 
	 * @param data A byte array, which contains a video in "video/mp4" datatype. The server expects H.264 Encoding,
	 * H.263 will not work in the web player
	 * @return "Got it" + the time and length of the received videos for debugging purposes
	 * @throws IOException
	 */
	@POST
	@Path("/addVideo")
	@Consumes("video/mp4")
	@Produces(MediaType.TEXT_PLAIN)
	public String addVideo ( @QueryParam("id") int id, byte[] data) throws IOException {
	
		File dir = new File("datastore/"+id+"/");
		dir.mkdirs();
		File file = new File("datastore/"+id+"/"+dir.list().length+".mp4");
		try {
			
			file.createNewFile();
			FileOutputStream output = new FileOutputStream(file);
			output.write(data);
			
			output.close();
			output = null;
			
			return "Got it! " + new Date()+",bytes: "+data.length;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "OK";//Inform the client that the video has been stored
	
	}
	
	/**
	 * This function returns a specific video chunk for a specific device ID, is used 
	 * to show videos from the server. Will work in browsers.
	 * @param id The sensor ID of the device
	 * @param count Which video should be returned
	 * @return A video as a byte array in video/mp4 format
	 * @throws IOException
	 */
	@GET
	@Path("/getVideo")
	@Produces({"video/mp4"})
	public byte[] getVideo (@QueryParam("id") int id, @QueryParam("count") int count) throws IOException {
		File file = new File("datastore/"+id+"/"+count+".mp4");
		return  Files.readAllBytes(file.toPath());
	}
	/**
	 * Returns an integer which corresponds to the latest video for a specific id.
	 * This can be used in conjunction with /getVideo to always play the latest video,
	 * as seen in the web interfaces live player
	 * @param id The device ID for which to return the latest id
	 * @return an integer corresponding to the latest video ID
	 * @throws IOException
	 */
	@GET
	@Path("/getLatest")
	@Produces(MediaType.TEXT_PLAIN)
	public String getLatest ( @QueryParam("id") int id) throws IOException {
		return (new File("datastore/"+id+"/").list().length-1)+"";
	}
	
/**
 * Gets all available VODs (Video on Demand) for a specific device ID. 
 * Videos with less than 8 seconds difference in their last modified times are
 * aggregated into one long video.
 * @param id The ideo for which to return the VOD
 * @return a VOD Array encoded as JSON, specifying at which ID and at what time the video starts and how long it is
 * @throws IOException
 */
	@GET
	@Path("/getVODS")
	@Produces(MediaType.APPLICATION_JSON)
	public VOD[] getVideo (@QueryParam("id") int id) throws IOException {
		File dir = new File("datastore/"+id+"/");
		File[] listOfFiles = dir.listFiles();
		//Sort the files
		Arrays.sort( listOfFiles, new Comparator<File>()
		{
		    public int compare(File o1, File o2) {
		
		        if (((File)o1).lastModified() < ((File)o2).lastModified()) {
		            return -1;
		        } else if (((File)o1).lastModified() > ((File)o2).lastModified()) {
		            return +1;
		        } else {
		            return 0;
		        }
		    }
		
		}); 
		ArrayList<VOD> vods = new ArrayList<VOD>();
		VOD current = null;
		long lastTime = 0 ;
		int length = 0;
		for (int i = 0; i < listOfFiles.length; i++){
			File f = listOfFiles[i];

			if (f.lastModified() < lastTime + 8000){//Video is continous
				lastTime = f.lastModified();
				length++;
			}
			else//New VOD
			{
				if (current != null){
					current.setLength(length);
					vods.add(current);
					lastTime = f.lastModified();
				}
				int count = Integer.parseInt( f.getName().replace(".mp4", ""));
				current = new VOD(count,f.lastModified(),length);
				lastTime = f.lastModified();
				length = 1;
			}
			
			if (i == listOfFiles.length-1){//Avoid fencepost problem
				int count = Integer.parseInt( f.getName().replace(".mp4", ""));
				current = new VOD(count-length+1,f.lastModified(),length);
				vods.add(current);
			}	
		}
		
		return (VOD[])(vods.toArray(new VOD[0]));
	}
	/**
	 * Simple helper web service to delete every video for a camera.
	 * The function simply deletes all files in the folder for a specific ID
	 * @param id The ID for which to delete all videos
	 * @return "OK" if all files have been deleted
	 * @throws IOException
	 */
	@GET
	@Path("/deleteEVERYTHING")
	@Produces(MediaType.TEXT_PLAIN)
	public String delete (@QueryParam("id") int id) throws IOException {
		for(File file: new File("datastore/"+id).listFiles()) file.delete();
		
		return "OK";
	}

}
