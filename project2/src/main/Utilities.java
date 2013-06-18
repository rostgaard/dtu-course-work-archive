package main;

public class Utilities {
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
}
