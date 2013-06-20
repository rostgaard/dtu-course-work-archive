package main;

public class Fob 
{
	private static long mySecret = 1337;
	
	
	public static boolean TryUnlock()
	{
		long ChallengeFromCar = Car.GetChallenge();
		
		long combined = Utilities.combine28bit(mySecret, ChallengeFromCar);
		
		long myResponse = Utilities.MD5_Hash(combined);
		
		boolean Success = Car.TryUnlock(myResponse);
		if(Success)
			System.out.println("Car is unlocked!");
		else
			System.out.println("Car is still locked");
		return Success;
	}

}
