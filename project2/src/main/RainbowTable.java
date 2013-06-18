/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.Serializable;
import java.util.HashMap;
import java.io.*;

/**
 * 
 * @author krc
 */
public class RainbowTable extends HashMap<Long, Long> implements Serializable {
	private static final long serialVersionUID = 8562121795254811164L;

	public static RainbowTable readFromFile(String path) {
		RainbowTable table = null;
		try {
			FileInputStream fileIn = new FileInputStream(path);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			table = (RainbowTable) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
		} catch (ClassNotFoundException c) {
			System.out.println("RainbowTable class not found");
			c.printStackTrace();
		}
		return table;
	}

	public static void writeToFile(RainbowTable rainbow, String path) {
		try {
			FileOutputStream fileOut = new FileOutputStream(path);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(rainbow);
			out.close();
			fileOut.close();
		} catch (IOException i) {
			i.printStackTrace();
		}
	}
}
