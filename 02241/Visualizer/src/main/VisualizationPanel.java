package main;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JFrame;

import parser.Parser;
import network.NetworkGraph;
import schedule.ScheduleGraph;
import status.StatusPanel;

/**
 * 
 * Creates and shows the visualization panel.
 * This panel contains the schedule panel, network panel 
 * and the status panel (in a single pane)
 *
 */

public class VisualizationPanel {

	/**
	 * Creates and shows the visualization panel (schedule, network and status panel)
	 * @param networkFile - name of the network file
	 * @param routeFile - name of the route file
	 */
	public void createAndShowGui(String networkFile, String routeFile){

		//create empty frame
		JFrame frame = new JFrame("Railway Simulation");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		//parse the given files
		Parser parser = new Parser(networkFile, routeFile);
						
		//create schedule panel and add to frame (upper left)
		ScheduleGraph schedulePanel = new ScheduleGraph(parser.getNodes(), parser.getRoutes());
		c.fill = GridBagConstraints.HORIZONTAL; 
	    c.gridx = 0;
	    c.gridy = 0;
		frame.getContentPane().add(schedulePanel, c);

		//create network panel and add to frame (upper right)
		NetworkGraph networkPanel = new NetworkGraph(parser.getNodes(), parser.getEdges());
		c.fill = GridBagConstraints.HORIZONTAL;
	    c.gridx = 1;
	    c.gridy = 0;
		frame.getContentPane().add(networkPanel.createPanel(schedulePanel.getPreferredSize().height), c);

		//create status panel and add to frame (bottom)
		StatusPanel statusPanel = new StatusPanel();
		c.fill = GridBagConstraints.HORIZONTAL;
	    c.gridwidth = 2;
	    c.gridx = 0;
	    c.gridy = 1;
	    frame.getContentPane().add(statusPanel.createPanel(schedulePanel.getPreferredSize().width), c);
		
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);
						
	}
}
