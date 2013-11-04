package junggraph;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JFrame;

/**
 * 
 * Creates and shows the visualization panel.
 * This panel contains the network graph panel 
 * and the status panel (in a single pane)
 *
 */

public class VisualizationPanel {
	
	private final int width = 500;	//width of the panel in pixels
	private final int height = 400;	//height of the panel in pixels
	
	/**
	 * Creates and shows the visualization panel (network graph and status panel)
	 */
	public void createAndShowGui(){
		
		//create empty frame
		JFrame frame = new JFrame("02242 Program Analysis");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		//Retrieve static data
		Data data = new Data();
		Graph graph = data.getStaticGraph();
								
		//create network graph panel and add to frame (top)
		NetworkGraph networkPanel = new NetworkGraph(graph);
		c.fill = GridBagConstraints.HORIZONTAL;
	    c.gridx = 0;
	    c.gridy = 0;
		frame.getContentPane().add(networkPanel.createPanel(width, height), c);
		
		//create status panel and add to frame (bottom)
		StatusPanel statusPanel = new StatusPanel();
		c.fill = GridBagConstraints.HORIZONTAL;
	    c.gridx = 0;
	    c.gridy = 1;
	    frame.getContentPane().add(statusPanel.createPanel(width), c);
		
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);
	}
}
