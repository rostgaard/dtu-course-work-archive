package main;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utilities {
	public static final long bit20 =    0xfffff;
	public static final long bit24 =   0xffffff;
	public static final long bit28 =  0xfffffff;
	public static final long bit32 = 0xffffffff;
	
	static long byteArrToLong(byte arg[]) {
		long value = 0;
		for (int i = 0; i < arg.length; i++) {
			value += ((long) arg[i] & 0xffL) << (8 * i);
		}
		return value;
	}
	
	static byte[] longToByteArr(long x){
		int size = 8;
		byte[] b = new byte[size];
		for (int i = 0; i < size; ++i) {
		  b[i] = (byte) (x >> (size - i - 1 << 3));
		}
		return b;
	}
	
	/**
	 * Combines x and y. X||Y
	 */
	static long combine(long x, long y, long bitmask) {
		int bitsUsed = (int) (Math.log(bitmask) / Math.log(2)) +1;
		long a = x & bitmask;
		long b = y & bitmask;
		return ((a << bitsUsed) + b);
	}

	static long reductionFunction(long cipherText, long reductionNumber,
			long tableSize) {
		return (cipherText + reductionNumber) % tableSize;
	}
	
	static long MD5_Hash(long arg, long mask) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			System.exit(-1);
		}

		md.update(longToByteArr(arg));
		byte byteData[] = md.digest();
		
		return Utilities.byteArrToLong(byteData) & mask;
	}
}
