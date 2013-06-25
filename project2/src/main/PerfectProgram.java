package main;

import java.io.File;
import java.security.NoSuchAlgorithmException;

public class PerfectProgram {
	public static void multipleSecretsPerfect() throws NoSuchAlgorithmException{
		long u = 1337L;
		long bitMask = Utilities.bit28;
		long rows = (long)Math.pow(2,18);
		long chainLength = (long)Math.pow(2, 10);
		PerfectRainbowTable rainbow = getPerfectRainbow(u,bitMask, rows, chainLength);
		int success = 0;
		for (int i = 1; i < 1000; i++) {
			if(robFobKey(rainbow, i, bitMask)){
				System.out.print("Guessed Secret: " + i);
				success += 1;
			}else{
				System.out.print("Failed secret: " + i);
			}
			double rate = ( ( (double)(success * 100) ) / ( (double) i) );
			System.out.println(" Success: " + success + " failed: " + (i-success) + " Total: " + i + " rate: " + rate);
		}
	 	System.out.println("Done");
	}
	
	static PerfectRainbowTable getPerfectRainbow(long u, long bitMask, long rows, long chainLength) throws NoSuchAlgorithmException{
		long bitsUsed = (long) (Math.log(bitMask) / Math.log(2)) +1;
		String filename = "U" + u + "_M" + rows + "_T" + chainLength + "_Bit" + bitsUsed + ".perfectrainbow";
		PerfectRainbowTable rainbow;
		
		//generate rainbow table with u
		if (new File(filename).exists()){
			rainbow = PerfectRainbowTableIO.readFromFile(filename);
		}else{
			rainbow = new PerfectRainbowTable(u, rows, chainLength);
			System.out.println("Generating rainbow table: " + filename);
			rainbow.generate();
			PerfectRainbowTableIO.writeToFile(rainbow, filename);
		}
		return rainbow;
	}
	
	static boolean robFobKey(PerfectRainbowTable rainbow, long secret, long bitmask) throws NoSuchAlgorithmException{
		// Sends the picked u to Fob, and gets r
		Fob fob = new Fob(secret);
		long response = fob.ChallengeMe(rainbow.u, bitmask);
		
		// Finds the r in rainbow, and from that extract the s
	 	long guessedSecret = rainbow.lookup(response, bitmask);
		
		//Verifiers the secret, by trying again.
	 	long newU = rainbow.u + 1;
	 	long guessedSecretHash = Utilities.MD5_Hash(Utilities.combine(guessedSecret, newU, bitmask), bitmask);
	 	long response1 = fob.ChallengeMe(newU, bitmask);
	 	if (guessedSecretHash == response1){
	 		return true;
	 	}
	 	return false;
	}
}
