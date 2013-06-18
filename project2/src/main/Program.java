package main;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
public class Program {
	final static long bit28 = 0xfffffff;
	static SecureRandom ran;
	
	public static void main(String[] args) throws NoSuchAlgorithmException {
		ran = new SecureRandom();
		generateRainbowTable();
		//test();
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
		long b = y & bit28;
		return ((a << 28) + b);
	}
	
	static long reductionFunction(long cipherText, long reductionNumber, long tableSize){
		return (cipherText + reductionNumber) % tableSize;
	}
	
	static String getRandomString(){
		final int textLenght = 8;
		String buffer = "";
		for (int i = 0; i < textLenght; i++){
			buffer += (char)(ran.nextInt(26) + 'A');
		}
		return buffer;
	}
	
	static RainbowTable generateRainbowTable(){
		RainbowTable rainbow = new RainbowTable();
		
		long length = (long) Math.pow(2, 10);
		long rows = (long) Math.pow(2, 18);
		Long lastTime = System.currentTimeMillis();
		for (int i = 0; i < rows; i++) {
			String startValue = getRandomString();
			long accumilator =  Utilities.byteArrToLong(startValue.getBytes());
			for (int j = 0; j < length; j++) {
				long cipher = MD5_Hash(accumilator);
				
				long reducedCipher = reductionFunction(cipher, j, bit28+1);
				accumilator = reducedCipher;
			}
			rainbow.put(accumilator, startValue);
		}
		
		return rainbow;
	}
	
	static void test(){
		
	}
}
