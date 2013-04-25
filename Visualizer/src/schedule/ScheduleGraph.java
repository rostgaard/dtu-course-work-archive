package schedule;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;
import javax.swing.border.LineBorder;

import domain.Node;

@SuppressWarnings("serial")
public class ScheduleGraph extends JPanel {

	private static final int X_AXIS_GAP = 65;
	private static final int Y_AXIS_GAP = 40;
	private static final int HATCH_GAP = 30;
	private static final int FINAL_GAP = 15;
	private static final int HATCH_GAP_WIDTH = 10;

	private int STATION_CNT;
	private int TIME_CNT;

	private ArrayList<Node> stations;
	private ArrayList<ArrayList<Node>> routes;
	private ArrayList<Color> colors;

	public ScheduleGraph(ArrayList<Node> stations, ArrayList<ArrayList<Node>> routes) {
		this.stations = stations;
		this.routes = routes;
		
		//generate random colors for each route
		Random randomGenerator = new Random();
		colors = new ArrayList<Color>();
		for(int i = 0; i<routes.size(); i++){
			int red = randomGenerator.nextInt(255);
			int green = randomGenerator.nextInt(255);
			int blue = randomGenerator.nextInt(255);
			Color randomColour = new Color(red,green,blue);
			colors.add(randomColour);
		}

		//find longest route
		int longest = 0;
		for(ArrayList<Node> route : routes){
			if (longest < route.size()){
				longest = route.size();
			}
		}
		
		STATION_CNT = stations.size();
		TIME_CNT = longest;
				
		setBorder(new LineBorder(Color.BLACK));
		setLayout(null);
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		// create x and y axes 
		g2.drawLine(X_AXIS_GAP, Y_AXIS_GAP, X_AXIS_GAP +(TIME_CNT*HATCH_GAP), Y_AXIS_GAP);
		g2.drawLine(X_AXIS_GAP, Y_AXIS_GAP, X_AXIS_GAP, Y_AXIS_GAP + (STATION_CNT * HATCH_GAP));

		// create hatch marks for y axis. 
		for(int i = 0; i < STATION_CNT; i++) {
			int x0 = X_AXIS_GAP - (HATCH_GAP_WIDTH/2);
			int x1 = x0 + HATCH_GAP_WIDTH;
			int y0 = (i * HATCH_GAP) + Y_AXIS_GAP + FINAL_GAP;
			int y1 = y0;
			g2.drawLine(x0, y0, x1, y1);
			
			//line markers½
			int max = (TIME_CNT * HATCH_GAP);
			g2.drawLine(x1, y1, x1+max, y1);
			
			g2.drawString(stations.get(i).toString(), 1, y0);
		}

		// create hatch marks for x axis.
		for (int i = 0; i < TIME_CNT; i++) {
			int x0 = (i * HATCH_GAP) + X_AXIS_GAP + FINAL_GAP;
			int x1 = x0;
			int y0 = Y_AXIS_GAP - (HATCH_GAP_WIDTH/2);
			int y1 = y0 + HATCH_GAP_WIDTH;
			g2.drawLine(x0, y0, x1, y1);

			//line markers			
			int max = (STATION_CNT * HATCH_GAP);
			g2.drawLine(x1, y1, x1, y1+max);
			
			g2.drawString("t"+i, x0, y0);
		}

		// create triangle for y axis.
		int[] xPoints = new int[3];
		int[] yPoints = new int[3];
		xPoints[0] = X_AXIS_GAP - (HATCH_GAP_WIDTH/2);
		xPoints[1] = xPoints[0] + HATCH_GAP_WIDTH;
		xPoints[2] = X_AXIS_GAP;

		yPoints[0] = Y_AXIS_GAP + (STATION_CNT * HATCH_GAP);
		yPoints[1] = yPoints[0];
		yPoints[2] = Y_AXIS_GAP + (STATION_CNT * HATCH_GAP) + FINAL_GAP;
		g.fillPolygon(xPoints, yPoints, 3);

		// create triangle for x axis.
		xPoints = new int[3];
		yPoints = new int[3];
		xPoints[0] = X_AXIS_GAP + (TIME_CNT * HATCH_GAP);
		xPoints[1] = xPoints[0];
		xPoints[2] = X_AXIS_GAP + (TIME_CNT * HATCH_GAP) + FINAL_GAP;

		yPoints[0] = Y_AXIS_GAP - (HATCH_GAP_WIDTH/2); 	
		yPoints[1] = yPoints[0] + HATCH_GAP_WIDTH;
		yPoints[2] = Y_AXIS_GAP;
		g.fillPolygon(xPoints, yPoints, 3);

		//draw the routes
		g2.setStroke(new BasicStroke(3));
		for(ArrayList<Node> route : routes){
			for(int i = 0; i<route.size()-1; i++){

				Node station = route.get(i);
				int x1 = X_AXIS_GAP + FINAL_GAP + (i*HATCH_GAP);
				int y1 = Y_AXIS_GAP +(HATCH_GAP * (stations.indexOf(station)+1)) - FINAL_GAP;

				Node station1 = route.get(i+1);
				int x2 = X_AXIS_GAP + FINAL_GAP + ((i+1)*HATCH_GAP);
				int y2 = Y_AXIS_GAP + (HATCH_GAP * (stations.indexOf(station1)+1)) - FINAL_GAP;

				Color c = colors.get(routes.indexOf(route));
				Color temp = new Color(c.getRed(), c.getGreen(), c.getBlue(), 200);

				g2.setColor(temp);
				g2.fillOval(x1-5, y1-5, 10, 10);

				//last station
				if(i == route.size()-2){
					g2.fillOval(x2-5, y2-5, 10, 10);
				}

				g2.setColor(c);
				g2.drawLine(x1, y1, x2, y2);
				
			}
			if(route.size() != TIME_CNT){
				Node station = route.get(route.size()-1);
				//draw line
				int x1 = X_AXIS_GAP + FINAL_GAP + (route.size()-1)*HATCH_GAP;
				int y1 = Y_AXIS_GAP +(HATCH_GAP * (stations.indexOf(station)+1)) - FINAL_GAP;
				int x2 = x1 + ((TIME_CNT - route.size()) * HATCH_GAP);
				g2.drawLine(x1, y1, x2, y1);

				//draw nodes	
				Color c = g2.getColor();
				Color temp = new Color(c.getRed(), c.getGreen(), c.getBlue(), 200);
				g2.setColor(temp);
				int nodes = TIME_CNT - route.size();				
				for(int i =0; i<nodes; i++){
					int x = X_AXIS_GAP + FINAL_GAP + (route.size())*HATCH_GAP + (i * HATCH_GAP);
					g2.fillOval(x-5, y1-5, 10, 10);
				}
			}
		}
	}

	@Override
	public Dimension getPreferredSize() {
		int width  = X_AXIS_GAP +(TIME_CNT*HATCH_GAP) + (2 * FINAL_GAP);
		int height = Y_AXIS_GAP + (STATION_CNT * HATCH_GAP) + (2 * FINAL_GAP);
		
		return new Dimension(width, height);
	}
}