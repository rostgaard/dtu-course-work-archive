
package web.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.DatatypeConverter;

import web.services.util.VideoPacket;

import com.sun.tools.ws.processor.util.DirectoryUtil;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import com.sun.xml.messaging.saaj.util.Base64;

/**
 * 
 * @author stefan
 *
 */

@LocalBean
@Stateless
@Path("/video")
public class VideoWebService {



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
			System.out.println("Error in Create file");
			e.printStackTrace();
		}
		return "";
	
	}
	
	
	@GET
	@Path("/getVideo")
	@Produces({"video/mp4"})
	public byte[] getVideo (@QueryParam("id") int id, @QueryParam("count") int count) throws IOException {
		File file = new File("datastore/"+id+"/"+count+".mp4");
		return  Files.readAllBytes(file.toPath());
	}
	@GET
	@Path("/getLatest")
	@Produces(MediaType.TEXT_PLAIN)
	public String getLatest ( @QueryParam("id") int id) throws IOException {
		return (new File("datastore/"+id+"/").list().length-1)+"";
	}
	
	@GET
	@Path("/deleteEVERYTHING")
	@Produces(MediaType.TEXT_PLAIN)
	public String delete (@QueryParam("id") int id) throws IOException {
		for(File file: new File("datastore/"+id).listFiles()) file.delete();
		
		return "OK";
	}

}
