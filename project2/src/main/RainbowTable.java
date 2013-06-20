/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * @author krc
 */
public class RainbowTable extends HashMap<Long, Long> implements Serializable {
	private static final long serialVersionUID = 8562121795254811164L;
	public long rows;
	public long chainLength;
	public long u;
	
	public RainbowTable(long u, long rows, long chainLength){
		super();
		this.u = u;
		this.rows = rows;
		this.chainLength = chainLength;
	}
	
	public void generate() {
		long bitMask = Utilities.bit28;
		SecureRandom ran = new SecureRandom();
		
		long lastTime = System.currentTimeMillis();
		for (int i = 0; i < this.rows; i++) {
			long startValue = ran.nextInt() % bitMask;
			long accumulator = startValue;
			for (int j = 0; j < this.chainLength; j++) {
				long cipher = Utilities.MD5_Hash(Utilities.combine(accumulator, this.u, bitMask), bitMask);
				long reducedCipher = Utilities.reductionFunction(cipher, j,	bitMask + 1);
				accumulator = reducedCipher;
			}
			put(accumulator, startValue);
		}

		long currentTime = System.currentTimeMillis();
		System.out.println("Generated the table in: "
				+ (currentTime - lastTime) / 1000);
	}
	
	public long lookup(long value, long mask) {
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

			if (containsKey(accumulator)) {
				Long startValue = get(accumulator);
				possibleStartValues.add(startValue);
			}
		}

		// Now that we know the row, we need to find the right key.

		for (Long startValue : possibleStartValues) {
			long accumilator = startValue;
			for (long i = 0; i < chainLength; i++) {
				long cipher = Utilities.MD5_Hash(Utilities.combine(accumilator, u, mask), mask);
				// Have we found the key?
				if (cipher == value) {
					return accumilator;
				}

				accumilator = Utilities.reductionFunction(cipher, i, mask + 1);
			}
		}
		return 0;
	}
}
