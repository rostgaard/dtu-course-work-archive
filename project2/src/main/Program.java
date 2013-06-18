package main;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Program {
	static SecureRandom ran;

	public static void main(String[] args) throws NoSuchAlgorithmException {
		ran = new SecureRandom();
		RainbowTable rainbow = generateRainbowTable();
		String path = "small.rainbow";
		RainbowTable.writeToFile(rainbow, path);

		RainbowTable testTable = RainbowTable.readFromFile(path);

		if (testTable != null) {
			System.out.println(rainbow.size() == testTable.size());
		}

		System.out.println("Main is done");
	}

	/**
	 * Combines x and y. X||Y
	 */
	static long combine28bit(long x, long y) {
		long a = x & Utilities.bit28;
		long b = y & Utilities.bit28;
		return ((a << 28) + b);
	}

	static long reductionFunction(long cipherText, long reductionNumber,
			long tableSize) {
		return (cipherText + reductionNumber) % tableSize;
	}

	static RainbowTable generateRainbowTable() {
		RainbowTable rainbow = new RainbowTable();

		long length = (long) Math.pow(2, 10);
		long rows = (long) Math.pow(2, 18);

		long lastTime = System.currentTimeMillis();
		for (int i = 0; i < rows; i++) {
			long startValue = ran.nextInt() % Utilities.bit28;
			long accumilator = startValue;
			for (int j = 0; j < length; j++) {
				long cipher = Utilities.MD5_Hash(accumilator);

				long reducedCipher = reductionFunction(cipher, j, Utilities.bit28 + 1);
				accumilator = reducedCipher;
			}
			rainbow.put(accumilator, startValue);
		}

		long currentTime = System.currentTimeMillis();
		System.out.println("Generated the table in: "
				+ (currentTime - lastTime));

		return rainbow;
	}
}
