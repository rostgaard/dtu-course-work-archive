package junggraph;

import javax.swing.SwingUtilities;

/**
 * 
 * Main class - Start the program here for visualization
 *
 */

public class Main {

	public static void main(String[] args) {	
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {	
				VisualizationPanel visualizationPanel = new VisualizationPanel();
				visualizationPanel.createAndShowGui();
			}
		});
	}
}
