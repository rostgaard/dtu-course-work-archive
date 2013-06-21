package main;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author krc
 */
public class test_MD5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NoSuchAlgorithmException {
        //System.out.println(Utilities.MD5_Hash(1000, Utilities.bit20));

        byte[] conv = new byte[1];
        conv[0] = 'a'; // Should be : 0cc175b9c0f1b6a831c399e269772661

        byte[] conv2 = new byte[5];
        conv2[0] = (byte) 0xe8;
        conv2[1] = (byte) 0x03;
       conv2[2] = (byte) 0x00;
       conv2[3] = (byte) 0x00;

       byte[] ab = new byte[2];
       ab[0] = 'a';
       ab[1] = 'b';
       
       byte[] abc = new byte[3];  //900150983cd24fb0d6963f7d28e17f72
       abc[0] = 'a';
       abc[1] = 'b';
       abc[2] = 'c';
       

        //byte[] conv3 = Utilities.longToByteArr(1000);
        byte[] conv3 = Utilities.longToByteArr(1000);

        System.out.println("toStringTest: " + Utilities.toString(abc));

        System.out.println();

        for (byte b : conv2) {
            System.out.format("%x", b);
        }
        System.out.println(" - "+ Utilities.toString(conv2) +"==" + Utilities.byteArrToLong(conv2));

        for (byte b : conv3) {
            System.out.format("%x", b);
        }
        System.out.println("==" + Utilities.byteArrToLong(conv3));

        for (byte b : abc) {
            System.out.format("%x", b);
        }
        System.out.println("==" + Utilities.byteArrToLong(abc));
        
        for (byte b : ab) {
            System.out.format("%x", b);
        }
        System.out.println("==" + Utilities.byteArrToLong(ab));
        
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            return;
        }

        md.update(conv);
        byte byteData[] = md.digest();

        for (byte b : byteData) {
            System.out.format("%x", b);
        }
        System.out.println();

        md.reset();
        md.update(conv2);
        byteData = md.digest();

        md.reset();
        md.update(abc);
        byteData = md.digest();

//        md.reset();
//        md.update(ab);
//        byteData = md.digest();
//        long hat = 
        		Utilities.MD5_Hash(1000, Utilities.bit28);
        
        System.out.println();
        assert 1 == 2;

        for (byte b : byteData) {
            System.out.format("%x", b);
        }
        System.out.println();
    }
}
