package web.services.util;

public class VideoPacket {
	int id;
	String content;
	
	public VideoPacket()
	{
		
	}
	public VideoPacket(int id, String content){
		this.id = id;
		this.content = content;
	}

	public int getId () {
		return id;
	}

	public void setId (int id) {
		this.id = id;
	}

	public String getContent () {
		return content;
	}

	public void setContent (String content) {
		this.content = content;
	}
}
