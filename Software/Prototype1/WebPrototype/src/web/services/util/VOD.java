package web.services.util;


public class VOD {
	long startTime;
	int startID;
	int length;
	
	public VOD()
	{
		
	}
	public VOD(int id, long start, int length){
		this.startID = id;
		this.startTime = start;
		this.length = length;
	}
	public int getLength () {
		return length;
	}
	public void setLength (int length) {
		this.length = length;
	}
	public long getStartTime () {
		return startTime;
	}
	public void setStartTime (long startTime) {
		this.startTime = startTime;
	}
	public int getStartID () {
		return startID;
	}
	public void setStartID (int startID) {
		this.startID = startID;
	}
}
