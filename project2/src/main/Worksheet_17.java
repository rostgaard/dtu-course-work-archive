package main;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Worksheet_17 {

	static SecureRandom ran;
	private static PrintWriter outfile;

	public static void main(String[] args) {
		ran = new SecureRandom();

		generateRainbowTable();
	}

	static RainbowTable generateRainbowTable() {
		final long bitmask = Utilities.bit20;
		RainbowTable rainbow = new RainbowTable();
		HashMap<Long, Long> Definition_Map = new HashMap<Long, Long>();
		String filename = "ex17.data";
		try {
			outfile = new PrintWriter(new FileWriter(filename));
		} catch (IOException ex) {

			Logger.getLogger(Worksheet_17.class.getName()).log(Level.SEVERE,
					null, ex);
		}

		long length = (long) Math.pow(2, 8);
		long rows = (long) Math.pow(2, 16);
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

				long reducedCipher = Utilities.reductionFunction(cipher, j, bitmask + 1);
				accumulator = reducedCipher;
				if (!Definition_Map.containsKey(reducedCipher)) {
					Definition_Map.put(reducedCipher, 0L);
				}
			}

			rainbow.put(accumulator, startValue);

			System.out.println("Added an element");
			double coverage = ((double) Definition_Map.size()) / Math.pow(2, 20);
			double end = (System.currentTimeMillis() - start) / 1000.0;

			System.out.format("%d %f %.8f %d \n", Definition_Map.size(), end, coverage, collisions);
			outfile.format("%f %.4f %d \n", end, coverage, collisions);
		}

		long currentTime = System.currentTimeMillis();
		System.out.println("Generated the table in: "
				+ (currentTime - lastTime) / 1000);
		outfile.close();
		return rainbow;
	}
}
