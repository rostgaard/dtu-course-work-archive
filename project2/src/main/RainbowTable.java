/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.*;

/**
 * 
 * @author krc
 */
public class RainbowTable extends HashMap<Long, Long> implements Serializable {
	private static final long serialVersionUID = 8562121795254811164L;
	public long rows;
	public long chainLength;

	public long lookup(long value) {
		ArrayList<Long> possibleStartValues = new ArrayList<Long>();
		for (int numReducFunc = 1; numReducFunc < chainLength; numReducFunc++) {
			long accumulator = value;
			for (long i = chainLength - numReducFunc; i < chainLength; i++) {
				accumulator = Utilities.reductionFunction(accumulator, i,
						Utilities.bit28 + 1);

				// Last iteration
				if (i < chainLength - 1) {
					accumulator = Utilities.MD5_Hash(accumulator);
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
				long cipher = Utilities.MD5_Hash(accumilator);

				// Have we found the key?
				if (cipher == value) {
					System.out.println("Value: " + value + " == Cipher: "
							+ cipher);
					return accumilator;
				}

				accumilator = Utilities.reductionFunction(cipher, i,
						Utilities.bit28 + 1);
			}
		}
		return 0;
	}
}
