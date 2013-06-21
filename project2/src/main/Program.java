package main;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class Program {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		HashMap<Long, Long> h = new HashMap<Long, Long>();
		System.out.println("Size: " + h.size());
		Long key = 42L;
		Long s1 = 1L;
		Long s2 = 2L;
		
		h.put(key, s1);
		System.out.println("Size: " + h.size());
		System.out.println("Key: " + key + " value: " + h.get(key));
		
		h.put(key, s2);
		System.out.println("Size: " + h.size());
		System.out.println("Key: " + key + " value: " + h.get(key));
		
		
		
		//generatingTest();
	}
	
	public static void multipleSecrets() throws NoSuchAlgorithmException{
		long u = 1337L;
		long bitMask = Utilities.bit28;
		long rows = (long)Math.pow(2,18);
		long chainLength = (long)Math.pow(2, 10);
		RainbowTable rainbow = getRainbow(u,bitMask, rows, chainLength);
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

	public static void generatingTest() {
		try {
			// Create file
			FileWriter fstream = new FileWriter("out.txt");
			BufferedWriter out = new BufferedWriter(fstream);
			
			long bitMask;

			long rows;
			long chainLength;
			long start;
			out.write("Rows: ChainLength: Bits: Time: ");
			out.flush();
//			for (int row = 18; row <= 24; row++) {
//				for (int length = 10; length <= 16; length++) {
//					bitMask = Utilities.bit28;
//					rows = (long)Math.pow(2,row);
//					chainLength = (long)Math.pow(2, length);
//					start = System.currentTimeMillis();
//					getRainbow(1337, bitMask, rows, chainLength);
//					out.write(rows + " " + chainLength + " " + "28" + " " + (System.currentTimeMillis()-start) + "\r\n");
//					out.flush();
//				}
//			}
			
			for (int row = 18; row <= 24; row++) {
				for (int length = 10; length <= 16; length++) {
					bitMask = Utilities.bit32;
					rows = (long)Math.pow(2,row);
					chainLength = (long)Math.pow(2, length);
					start = System.currentTimeMillis();
					getRainbow(1337, bitMask, rows, chainLength);
					out.write(rows + " " + chainLength + " " + "32" + " " + (System.currentTimeMillis()-start) + "\r\n");
					out.flush();
				}
			}
			
			// Close the output stream
			out.close();
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
	}
	
	static RainbowTable getRainbow(long u, long bitMask, long rows, long chainLength) throws NoSuchAlgorithmException{
		long bitsUsed = (long) (Math.log(bitMask) / Math.log(2)) +1;
		String filename = "U" + u + "_M" + rows + "_T" + chainLength + "_Bit" + bitsUsed + ".rainbow";
		RainbowTable rainbow;
		
		//generate rainbow table with u
		if (new File(filename).exists()){
			rainbow = RainbowTableIO.readFromFile(filename);
		}else{
			rainbow = new RainbowTable(u, rows, chainLength);
			System.out.println("Generating rainbow table: " + filename);
			rainbow.generate();
			RainbowTableIO.writeToFile(rainbow, filename);
		}
		return rainbow;
	}
	
	static boolean robFobKey(RainbowTable rainbow, long secret, long bitmask) throws NoSuchAlgorithmException{
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
