package main;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utilities {
	public static final long bit28 = 0xfffffff;
	
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
	
	static long MD5_Hash(long arg) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			System.exit(-1);
		}

		md.update(longToByteArr(arg));
		byte byteData[] = md.digest();
		
		return Utilities.byteArrToLong(byteData) & bit28;
	}
}
