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
		byte[] res = new byte[8];
		for(int i = 0; i < 8; i ++){
			res[i] = (byte) (x & (0xff << (8 * i)) >> (8 * i));
		}
		return res;
	}
}
