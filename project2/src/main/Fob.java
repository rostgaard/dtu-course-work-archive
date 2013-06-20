package main;

public class Fob 
{	
	public long secret;
	public Fob(long secret){
		this.secret = secret;
	}
	
	public long ChallengeMe(long challenge, long bitmask){
		long concat = Utilities.combine(secret, challenge, bitmask);
		long response = Utilities.MD5_Hash(concat, Utilities.bit28);
		return response;
	}

}
