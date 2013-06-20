package main;

import java.io.File;
import java.security.NoSuchAlgorithmException;

public class Program {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		long u = 1337L;
		long bitMask = Utilities.bit28;
		RainbowTable rainbow = getRainbow(u,bitMask);
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
	
	static RainbowTable getRainbow(long u, long bitMask){
		long rows = (long)Math.pow(2,18);
		long chainLength = (long)Math.pow(2, 10);
		int bitsUsed = (int) (Math.log(bitMask) / Math.log(2)) +1;
		String filename = "U" + u + "_M" + rows + "_T" + chainLength + "_Bit" + bitsUsed + ".rainbow";
		RainbowTable rainbow;
		
		//generate rainbow table with u
		if (new File(filename).exists()){
			rainbow = RainbowTableIO.readFromFile(filename);
		}else{
			rainbow = new RainbowTable(u, rows, chainLength);
			System.out.println("Generating rainbow table");
			rainbow.generate();
			RainbowTableIO.writeToFile(rainbow, filename);
		}
		return rainbow;
	}
	
	static boolean robFobKey(RainbowTable rainbow, long secret, long bitmask){
		// Sends the picked u to Fob, and gets r
		Fob fob = new Fob(secret);
		long response = fob.ChallengeMe(rainbow.u, bitmask);
		//System.out.println("Real Secret: " + fob.secret);
		//System.out.println("Reponse: " + response);
		
		// Finds the r in rainbow, and from that extract the s
	 	long guessedSecret = rainbow.lookup(response, bitmask);
	 	//System.out.println("guessedSecret: " + guessedSecret);
		
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
