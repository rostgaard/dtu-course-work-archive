package main;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Worksheet_15 {

	static SecureRandom ran;
	private static PrintWriter outfile;

	public static void main(String[] args) throws NoSuchAlgorithmException {
		ran = new SecureRandom();

		generateRainbowTable();
	}

	static RainbowTable generateRainbowTable() throws NoSuchAlgorithmException {
		final long bitmask = Utilities.bit20;
		long u = 1;
		long length = (long) Math.pow(2, 8);
		long rows = (long) Math.pow(2, 16);
		RainbowTable rainbow = new RainbowTable(u, rows, length);
		HashMap<Long, Long> Definition_Map = new HashMap<Long, Long>();
		String filename = "ex15.data";
		try {
			outfile = new PrintWriter(new FileWriter(filename));
		} catch (IOException ex) {

			Logger.getLogger(Worksheet_15.class.getName()).log(Level.SEVERE, null, ex);
		}

		long collisions = 0;
		long start = System.currentTimeMillis();
		rainbow.rows = rows;
		rainbow.chainLength = length;

		long lastTime = System.currentTimeMillis();
		for (int i = 0; i < rows; i++) {
			long startValue = ran.nextInt() % bitmask;
			long accumulator = startValue;

			// Making a chain.
			for (int j = 0; j < length; j++) {
				long cipher = Utilities.MD5_Hash(accumulator, bitmask);

				long reducedCipher = Utilities.reductionFunction(cipher, 1, bitmask + 1);
				accumulator = reducedCipher;
				if (!Definition_Map.containsKey(reducedCipher)) {
					Definition_Map.put(reducedCipher, 0L);
				}
			}

			rainbow.put(accumulator, startValue);

			double coverage = ((double) Definition_Map.size()) / Math.pow(2, 20);
			double end = (System.currentTimeMillis() - start) / 1000.0;

			outfile.format("%f %.4f \n", end, coverage);
		}

		long currentTime = System.currentTimeMillis();
		System.out.println("Generated the table in: " + (currentTime - lastTime) / 1000);
		outfile.close();
		return rainbow;
	}
}
