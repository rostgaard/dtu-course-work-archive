package main;

import java.security.NoSuchAlgorithmException;

/**
 * Simulating a remote key.
 */
public class Fob 
{	
	public long secret;
	public Fob(long secret){
		this.secret = secret;
	}
	
	/**
	 * Sends it a challenge, to which it gives the response.
	 * @param challenge Challenge send from the car
	 * @param bitmask How many bits are used
	 * @return MD5 of secret || challenge
	 * @throws NoSuchAlgorithmException
	 */
	public long ChallengeMe(long challenge, long bitmask) throws NoSuchAlgorithmException{
		long concat = Utilities.combine(secret, challenge, bitmask);
		long response = Utilities.MD5_Hash(concat, Utilities.bit28);
		return response;
	}

}
