package main;

import javax.swing.SwingUtilities;

/**
 * Main class - Start the program here
 *
 */

public class Main {

	public static void main(String[] args) {	
		if(args.length == 2) {
			//get file name
			final String networkFile = args[0];
			final String routeFile = args[1]; 
						
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {	
					VisualizationPanel visualizationPanel = new VisualizationPanel();
					visualizationPanel.createAndShowGui(networkFile, routeFile);
				}
			});
		}else{
			//if files are not given, then throw an exception
			throw new RuntimeException("Expected 2 input files as argument (railway network and rutes)!");
		}
	}
}
