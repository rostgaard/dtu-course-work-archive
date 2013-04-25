package main;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import parser.Parser;
import network.NetworkGraph;
import schedule.ScheduleGraph;

public class Main {

	public static void main(String[] args) {	
		if(args.length == 2) {
			final String networkFile = args[0];
			final String ruteFile = args[1]; 
						
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {		
					createAndShowGui(networkFile, ruteFile);
				}
			});
			
		}else{
			throw new RuntimeException("Expected 2 input files as argument (railway network and rutes)!");
		}
	}
	
	public static void createAndShowGui(String networkFile, String ruteFile){
		JFrame frame = new JFrame("Train Simulation");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		Parser p = new Parser();
		p.readNetwork(networkFile);
		p.readRutes(ruteFile);
				
		ScheduleGraph schedulePanel = new ScheduleGraph(p.getNodes(), p.getRoutes());
		c.fill = GridBagConstraints.HORIZONTAL;
	    c.ipady = 1; 
	    c.weightx = 1;
	    c.gridwidth = 1;
	    c.gridx = 0;
	    c.gridy = 1;
		frame.getContentPane().add(schedulePanel, c);

		NetworkGraph networkPanel = new NetworkGraph(p.getNodes(), p.getEdges());
		c.fill = GridBagConstraints.HORIZONTAL;
	    c.ipady = 1; 
	    c.weightx = 1;
	    c.gridwidth = 1;
	    c.gridx = 1;
	    c.gridy = 1;
		frame.getContentPane().add(networkPanel.getPanel(450, schedulePanel.getPreferredSize().height), c);
		
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);
	}
}
