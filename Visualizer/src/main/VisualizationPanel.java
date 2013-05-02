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
 * This panel contains the schedule panel, network panel and the status panel
 *
 */

public class VisualizationPanel {

	public void createAndShowGui(String networkFile, String routeFile){

		//create empty frame
		JFrame frame = new JFrame("Train Simulation");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		//parse the given files
		Parser parser = new Parser(networkFile, routeFile);
						
		//create schedule panel and add to frame
		ScheduleGraph schedulePanel = new ScheduleGraph(parser.getNodes(), parser.getRoutes());
		c.fill = GridBagConstraints.HORIZONTAL; 
	    c.gridx = 0;
	    c.gridy = 0;
		frame.getContentPane().add(schedulePanel, c);

		//create network panel and add to frame
		NetworkGraph networkPanel = new NetworkGraph(parser.getNodes(), parser.getEdges());
		c.fill = GridBagConstraints.HORIZONTAL;
	    c.gridx = 1;
	    c.gridy = 0;
		frame.getContentPane().add(networkPanel.getPanel(schedulePanel.getPreferredSize().height), c);

		//create status panel and add to frame
		StatusPanel statusPanel = new StatusPanel();
		c.fill = GridBagConstraints.HORIZONTAL;
	    c.gridwidth = 2;
	    c.gridx = 0;
	    c.gridy = 1;
	    frame.getContentPane().add(statusPanel.getPanel(schedulePanel.getPreferredSize().width), c);
		
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);
						
	}
}
