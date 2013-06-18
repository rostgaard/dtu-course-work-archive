package main;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Program {
	final static long bit28 = 0xfffffff;
	public static void main(String[] args) throws NoSuchAlgorithmException {
		long result = MD5_Hash(45L);
		System.out.println(result);
	}

	static long MD5_Hash(long arg) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			System.exit(-1);
		}

		md.update(Utilities.longToByteArr(arg));
		byte byteData[] = md.digest();
				
		return Utilities.byteArrToLong(byteData) & bit28;
	}

	/**
	 * Combines x and y. X||Y
	 */
	static long combine28bit(long x, long y){
		long a = x & bit28;
		return ((a << 28) + y);
	}
	
	static long reductionFunction(long cipherText, long reductionNumber, long tableSize){
		return (cipherText + reductionNumber) % tableSize;
	}
}
