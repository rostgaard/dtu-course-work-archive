package main;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;

public class PerfectRainbowTable extends HashMap<Long, Long> implements
		Serializable {
	private static final long serialVersionUID = -8477678838986093982L;
	public long rows;
	public long chainLength;
	public long u;
	public long bitMask;

	public PerfectRainbowTable(long u, long rows, long chainLength, long bitMask) {
		super();
		this.u = u;
		this.rows = rows;
		this.chainLength = chainLength;
		this.bitMask = bitMask;
	}

	public void generate() throws NoSuchAlgorithmException {
		//long bitMask = Utilities.bit28;
		SecureRandom ran = new SecureRandom();

		long lastTime = System.currentTimeMillis();
		while (size() <= this.rows) {
			long startValue = ran.nextInt() % bitMask;
			long accumulator = startValue;
			for (int j = 0; j < this.chainLength; j++) {
				long cipher = Utilities.MD5_Hash(Utilities.combine(accumulator, this.u, bitMask),bitMask);
				long reducedCipher = Utilities.reductionFunction(cipher, j, bitMask + 1);
				accumulator = reducedCipher;
			}
			if (!containsKey(accumulator)){
				put(accumulator, startValue);
			}
		}

		long currentTime = System.currentTimeMillis();
		System.out.println("Generated the table in: "
				+ (currentTime - lastTime) / 1000);
	}

	public long lookup(long value, long mask) throws NoSuchAlgorithmException {
		//saves all candidates for a key.
		ArrayList<Long> possibleStartValues = new ArrayList<Long>();
		for (int numReducFunc = 1; numReducFunc < chainLength; numReducFunc++) {
			long accumulator = value;
			for (long i = chainLength - numReducFunc; i < chainLength; i++) {
				accumulator = Utilities.reductionFunction(accumulator, i, mask + 1);

				// Last iteration
				if (i < chainLength - 1) {
					long combined = Utilities.combine(accumulator, this.u, mask);
					accumulator = Utilities.MD5_Hash(combined, mask);
				}
			}

			if (this.containsKey(accumulator)) {
				possibleStartValues.add(get(accumulator));

			}
		}

		//Now we have some candidates. Now we need to check for duplicates, and by that find a key. 
		for (Long startValue : possibleStartValues) {
			long accumilator = startValue;
			for (long i = 0; i < chainLength; i++) {
				long cipher = Utilities.MD5_Hash(Utilities.combine(accumilator, u, mask), mask);
				
				// Have we found a valid key?
				if (cipher == value) {
					return accumilator;
				}

				accumilator = Utilities.reductionFunction(cipher, i, mask + 1);
			}
		}
		return 0;
	}
}
