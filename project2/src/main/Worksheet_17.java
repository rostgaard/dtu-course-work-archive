package main;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Worksheet_17 {

    static SecureRandom ran;
    private static PrintWriter outfile;

    public static void main(String[] args) {
        ran = new SecureRandom();

        RainbowTable rainbow = generateRainbowTable();
    }

    static RainbowTable generateRainbowTable() {
        RainbowTable rainbow = new RainbowTable();
        String filename = "ex17.data";
        try {
            outfile = new PrintWriter(new FileWriter(filename));
        } catch (IOException ex) {

            Logger.getLogger(Worksheet_17.class.getName()).log(Level.SEVERE, null, ex);
        }

        long length = (long) Math.pow(2, 8);
        long rows = (long) Math.pow(2, 16);
        long collisions = 0;
        long start = System.currentTimeMillis();
        rainbow.rows = rows;
        rainbow.chainLength = length;

        long lastTime = System.currentTimeMillis();
        for (int i = 0; i < rows; i++) {
            long startValue = ran.nextInt() % Utilities.bit20;
            long accumilator = startValue;
            for (int j = 0; j < length; j++) {
                long cipher = Utilities.MD5_Hash(accumilator);

                long reducedCipher = Utilities.reductionFunction(cipher, j, Utilities.bit20 + 1);
                accumilator = reducedCipher;
            }
            if (!rainbow.containsKey(accumilator)) {
                rainbow.put(accumilator, startValue);
            } else {
                long prevval = rainbow.size();
                rainbow.put(accumilator, startValue);
            }
                System.out.println("Added an element");
            double coverage = ((double)rainbow.size()) / Math.pow(2, 20);
            double end = (System.currentTimeMillis() - start) / 1000.0;
            
            System.out.format("%d %f %.8f %d \n" ,rainbow.size(), end, coverage, collisions);
            outfile.format("%f %.4f %d \n" , end, coverage, collisions);
        }

        long currentTime = System.currentTimeMillis();
        System.out.println("Generated the table in: "
                + (currentTime - lastTime) / 1000);
        outfile.close();
        return rainbow;
    }
}
