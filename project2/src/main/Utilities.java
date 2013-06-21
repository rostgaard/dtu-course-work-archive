package main;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public abstract class Utilities {
    public static final long bit20 = 0xfffff;
    public static final long bit24 = 0xffffff;
    public static final long bit28 = 0xfffffff;
    public static final long bit32 = 0xffffffff;
    
    public static String toString(byte array[]) {
        String buffer = "";
        
        for (int i = array.length-1; i >= 0; i--) {
            buffer += String.format("%x", array[i]);
        }        
        return buffer;
    }

    public static void reverse (byte array[]) {
        for (int i = 0; i < array.length / 2; i++) {
            byte temp = array[i];
            array[i] = array[array.length - i - 1];
            array[array.length - i - 1] = temp;
        }
        
    }
    
    static long byteArrToLong(byte arg[]) {
        long value = 0;
        for (int i = 0; i < arg.length; i++) {
            value += ((long) arg[i] & 0xffL) << (8 * i);
        }
        return value;
    }

    static byte[] longToByteArr(long x) {
        int size = 8;
        byte[] b = new byte[size];
        for (int i = 0; i < size; ++i) {
            b[i] = (byte) (x >> (size - i - 1 << 3));
        }
        // Reverse the array to enable correct byte order for use with MD5.
        // TODO: integrate it into the construction, instead of reversing it
        //       afterwads. It seems.. Silly and inefficient.
        reverse(b);
        
        return b;
    }

    /**
     * Combines x and y. X||Y
     */
    static long combine(long x, long y, long bitmask) {
        int bitsUsed = (int) (Math.log(bitmask) / Math.log(2)) + 1;
        long a = x & bitmask;
        long b = y & bitmask;
        return ((a << bitsUsed) + b);
    }

    static long reductionFunction(long cipherText, long reductionNumber,
            long tableSize) {
        return (cipherText + reductionNumber) % tableSize;
    }

    static long MD5_Hash(long arg, long mask) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(longToByteArr(arg));
        byte byteData[] = md.digest();

        return Utilities.byteArrToLong(byteData) & mask;
    }
}
