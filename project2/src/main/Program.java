package main;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Program {
	static SecureRandom ran;

	public static void main(String[] args) throws NoSuchAlgorithmException {
		ran = new SecureRandom();

		// RainbowTable rainbow = generateRainbowTable();
		String path = "small1.rainbow";
		// RainbowTableIO.writeToFile(rainbow, path);

		RainbowTable testTable = RainbowTableIO.readFromFile(path);

		long plain = 1535;
		long hash = Utilities.MD5_Hash(plain, Utilities.bit28);
		long plainGuess = testTable.lookup(hash, Utilities.bit28);

		System.out.println("Real: " + plain);
		System.out.println("Guess: " + plainGuess);

		long guessHashed = Utilities.MD5_Hash(plainGuess, Utilities.bit28);
		System.out.println("original hash:" + hash + " guessedHashed: "
				+ guessHashed);

		System.out.println("Main is done");
	}

	static RainbowTable generateRainbowTable() {
		RainbowTable rainbow = new RainbowTable();

		long length = (long) Math.pow(2, 10);
		long rows = (long) Math.pow(2, 18);
		rainbow.rows = rows;
		rainbow.chainLength = length;

		long lastTime = System.currentTimeMillis();
		for (int i = 0; i < rows; i++) {
			long startValue = ran.nextInt() % Utilities.bit28;
			long accumulator = startValue;
			for (int j = 0; j < length; j++) {
				long cipher = Utilities.MD5_Hash(accumulator, Utilities.bit28);

				long reducedCipher = Utilities.reductionFunction(cipher, j,
						Utilities.bit28 + 1);
				accumulator = reducedCipher;
			}
			rainbow.put(accumulator, startValue);
			System.out.print(rainbow.size());
		}

		long currentTime = System.currentTimeMillis();
		System.out.println("Generated the table in: "
				+ (currentTime - lastTime) / 1000);

		return rainbow;
	}
}
