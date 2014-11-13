
package web.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import com.sun.xml.messaging.saaj.util.Base64;

@Path("/video")
public class VideoWebService {

	@POST
	@Path("/addVideo")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String addVideo ( VideoPacket in) throws IOException {

	
		File file = new File("datastore/"+in.getId()+"/"+new Date().getTime()+".mp4");
		File dir = new File("datastore/"+in.getId()+"/");
		dir.mkdirs();
		file.createNewFile();
		
		InputStream inputStream = new ByteInputStream();
		byte[] decoded = DatatypeConverter.parseBase64Binary(in.getContent());
		
		FileOutputStream output = new FileOutputStream(file);
		output.write(decoded);
		output.flush();
		output.close();
		inputStream.close();
		inputStream = null;
		output = null;
		//TODO: Store a string in the database which references the video
		return "Got it! " + new Date()+",bytes: "+decoded.length;
	}
}
