package web.services.util;

public class VOD {
	long startTime;
	int startID;
	
	public VOD()
	{
		
	}
	public VOD(int id, long start){
		this.startID = id;
		this.startTime = start;
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
