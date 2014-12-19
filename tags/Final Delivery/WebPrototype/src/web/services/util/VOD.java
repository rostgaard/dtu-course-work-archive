package web.services.util;
/**
 * 
 * @author stefan s113420
 *
 */

/**
 * 
 * @author 
 *
 */

public class VOD {
	long startTime;//The time at which the VOD starts
	int startID;//The video ID at which the VOD starts
	int length;//The length in video chuncks (default: 4s)
	
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
