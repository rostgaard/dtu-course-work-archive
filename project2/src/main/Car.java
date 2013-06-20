package main;

import java.security.SecureRandom;

public class Car {
	static SecureRandom ran;
	private static int MySecret = 1337;
	private static long Challenge; 
	private static boolean ChallengeSent = true; //Initialized to true, as this will generate a new Challenge
	
	private static void GenerateChallenge()
	{
		SecureRandom ran = new SecureRandom();
		Challenge = ran.nextInt() % Utilities.bit28;
		ChallengeSent = false;
	}
	
	//Broadcasts a new challenge-number. Ensures that a new random value is sent every time. 
	public static long GetChallenge()
	{
		if(ChallengeSent)
			GenerateChallenge();
		ChallengeSent = true;
		return Challenge;			
	}
	
	public static boolean TryUnlock(long Response, long bitmask)
	{
		long combined = Utilities.combine(MySecret, Challenge, bitmask);
		long myResponse = Utilities.MD5_Hash(combined, Utilities.bit28);
		System.out.println("Car Secret: " + MySecret + ", CarChallenge: " + Challenge + ", Hash(Secret||CarChallenge): " + myResponse + ", Received: " + Response);
		return (myResponse == Response);
	}
}
