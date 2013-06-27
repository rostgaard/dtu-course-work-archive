package main;

import java.io.*;
import java.security.NoSuchAlgorithmException;

public class Program {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        //multipleSecrets(1000);
        //multipleSecretsPerfect(1000);
        nonPerfectVSPerfect(1000);
    }

    public static void multipleSecrets(int numberOfTests) throws NoSuchAlgorithmException {
        long u = 1337L;
        long bitMask = Utilities.bit28;
        long rows = (long) Math.pow(2, 18);
        long chainLength = (long) Math.pow(2, 10);
        RainbowTable rainbow = getRainbow(u, bitMask, rows, chainLength);
        int success = 0;
        for (int i = 1; i < numberOfTests; i++) {
            if (robFobKey(rainbow, i, bitMask)) {
                System.out.print("Guessed Secret: " + i);
                success += 1;
            } else {
                System.out.print("Failed secret: " + i);
            }
            double rate = (((double) (success * 100)) / ((double) i));
            System.out.println(" Success: " + success + " failed: " + (i - success) + " Total: " + i + " rate: " + rate);
        }
        System.out.println("Done");
    }

    public static void multipleSecretsPerfect(int numberOfTests) throws NoSuchAlgorithmException {
        long u = 1337L;
        long bitMask = Utilities.bit28;
        long rows = (long) Math.pow(2, 18);
        long chainLength = (long) Math.pow(2, 10);

        PerfectRainbowTable rainbow = getPerfectRainbow(u, bitMask, rows, chainLength);
        int success = 0;
        for (int i = 1; i < numberOfTests; i++) {
            if (robFobKey(rainbow, i, bitMask)) {
                System.out.print("Guessed Secret: " + i);
                success += 1;
            } else {
                System.out.print("Failed secret: " + i);
            }
            double rate = (((double) (success * 100)) / ((double) i));
            System.out.println(" Success: " + success + " failed: " + (i - success) + " Total: " + i + " rate: " + rate);
        }
        System.out.println("Done");
    }

    static void nonPerfectVSPerfect(int numberOfTests) throws NoSuchAlgorithmException {
        long u = 1337L;
        long bitMask = Utilities.bit20;
        long rows = (long) Math.pow(2, 18);
        long chainLength = (long) Math.pow(2, 10);

        PerfectRainbowTable perfectRainbow = getPerfectRainbow(u, bitMask, rows, chainLength);
        RainbowTable rainbow = getRainbow(u, bitMask, rows, chainLength);
        int success = 0;
        int perfectSucces = 0;
        for (int i = 1; i <= numberOfTests; i++) {
            int secret = i;
            if (robFobKey(rainbow, secret, bitMask)) {
                success += 1;
            }
            double rate = (((double) (success * 100)) / ((double) i));
            System.out.println("nonPerfect Success: " + success + " failed: " + (i - success) + " Total: " + i + " rate: " + rate);

            if (robFobKey(perfectRainbow, secret, bitMask)) {
                perfectSucces += 1;
            }
            rate = (((double) (perfectSucces * 100)) / ((double) i));
            System.out.println("Perfect Success: " + perfectSucces + " failed: " + (i - perfectSucces) + " Total: " + i + " rate: " + rate);
            System.out.println();
        }
        System.out.println("Done");
    }

    static PerfectRainbowTable getPerfectRainbow(long u, long bitMask, long rows, long chainLength) throws NoSuchAlgorithmException {
        long bitsUsed = (long) (Math.log(bitMask) / Math.log(2)) + 1;
        String filename = "U" + u + "_M" + rows + "_T" + chainLength + "_Bit" + bitsUsed + ".perfectrainbow";
        PerfectRainbowTable rainbow;

        //generate rainbow table with u
        if (new File(filename).exists()) {
            rainbow = PerfectRainbowTableIO.readFromFile(filename);
        } else {
            rainbow = new PerfectRainbowTable(u, rows, chainLength, bitMask);
            System.out.println("Generating rainbow table: " + filename);
            rainbow.generate();
            PerfectRainbowTableIO.writeToFile(rainbow, filename);
        }
        return rainbow;
    }

    static RainbowTable getRainbow(long u, long bitMask, long rows, long chainLength) throws NoSuchAlgorithmException {
        long bitsUsed = (long) (Math.log(bitMask) / Math.log(2)) + 1;
        String filename = "U" + u + "_M" + rows + "_T" + chainLength + "_Bit" + bitsUsed + ".rainbow";
        RainbowTable rainbow;

        //generate rainbow table with u
        if (new File(filename).exists()) {
            rainbow = RainbowTableIO.readFromFile(filename);
        } else {
            rainbow = new RainbowTable(u, rows, chainLength, bitMask);
            System.out.println("Generating rainbow table: " + filename);
            rainbow.generate();
            RainbowTableIO.writeToFile(rainbow, filename);
        }
        return rainbow;
    }

    static boolean robFobKey(RainbowTable rainbow, long secret, long bitmask) throws NoSuchAlgorithmException {
        // Sends the picked u to Fob, and gets r
        Fob fob = new Fob(secret);
        long response = fob.ChallengeMe(rainbow.u, bitmask);

        // Finds the r in rainbow, and from that extract the s
        long guessedSecret = rainbow.lookup(response, bitmask);

        //Verifiers the secret, by trying again.
        long newU = rainbow.u + 1;
        long guessedSecretHash = Utilities.MD5_Hash(Utilities.combine(guessedSecret, newU, bitmask), bitmask);
        long response1 = fob.ChallengeMe(newU, bitmask);
        if (guessedSecretHash == response1) {
            return true;
        }
        return false;
    }

    static boolean robFobKey(PerfectRainbowTable rainbow, long secret, long bitmask) throws NoSuchAlgorithmException {
        // Sends the picked u to Fob, and gets r
        Fob fob = new Fob(secret);
        long response = fob.ChallengeMe(rainbow.u, bitmask);

        // Finds the r in rainbow, and from that extract the s
        long guessedSecret = rainbow.lookup(response, bitmask);

        //Verifiers the secret, by trying again.
        long newU = rainbow.u + 1;
        long guessedSecretHash = Utilities.MD5_Hash(Utilities.combine(guessedSecret, newU, bitmask), bitmask);
        long response1 = fob.ChallengeMe(newU, bitmask);
        if (guessedSecretHash == response1) {
            return true;
        }
        return false;
    }
}
