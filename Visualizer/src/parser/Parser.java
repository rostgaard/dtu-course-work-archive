package parser;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

import domain.Edge;
import domain.Node;

/**
 * 
 * Parser.
 * Parses the two files (network and route configuration)
 * Has responsibility to parse files and create proper object types 
 *
 */

public class Parser {

	private HashMap<String, Node> nodes;
	private ArrayList<Edge> edges;	
	private ArrayList<ArrayList<Node>> routes;

	/**
	 * Constructor
	 * @param networkFile - network configuration file
	 * @param routeFile  - route configuration file
	 */
	public Parser(String networkFile, String routeFile){
		this.nodes = new HashMap<String, Node>();
		this.edges = new ArrayList<Edge>();
		this.routes = new ArrayList<ArrayList<Node>>();
		
		readNetwork(networkFile);
		readRoutes(routeFile);
	}
	
	/***
	 * Read and parse the network configuration
	 * @param filename - name of the file for network configuration
	 */
	private void readNetwork(String filename){
		try{
			FileInputStream fstream = new FileInputStream(filename);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));

			String strLine;
			//Read File Line By Line
			while ((strLine = br.readLine()) != null) 	{
				checkStation(strLine);
				checkConnection(strLine);
			}
			//Close the input stream
			in.close();
			if(nodes.isEmpty()){
				System.err.println("No station/nodes declared");
				System.exit(0);
			}
		}catch (Exception e){//Catch exception if any
			System.err.println(e.getMessage());
			System.exit(0);
		}

	}

	/**
	 * Read and parse the route configuration
	 * @param filename - name of the file for route configuration
	 */
	private void readRoutes(String filename){
		try{
			FileInputStream fstream = new FileInputStream(filename);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));

			String strLine;
			//Read File Line By Line
			while ((strLine = br.readLine()) != null) 	{
				checkRoute(strLine);
			}
			//Close the input stream
			in.close();
			if(routes.isEmpty()){
				System.err.println("No routes declared");
				System.exit(0);
			}
		}catch (Exception e){//Catch exception if any
			System.err.println(e.getMessage());
			System.exit(0);
		}
	}
	
	/**
	 * Checks that a station (STAT) is found, if so it becomes parsed
	 * @param strLine
	 */
	private void checkStation(String strLine){
		String[] tokens = strLine.split(" ");
		//check for stations (STAT)
		if(tokens[0].equals("STAT")){
			//length of tokens should be 3 for stations
			if(tokens.length == 3){
				String name = tokens[1]; //name for station
				String id = tokens[2]; //id for station
				//add station if not contained in the set
				if(!nodes.containsKey(id)){
					Node node = new Node(id, name);
					nodes.put(id, node);
				}
			}else{
				System.err.println("Station was not not given correctly please correct statement: " + strLine);
				System.exit(0);
			}
		}
	}

	/**
	 * Checks that a connection (CONN) is found, if so it becomes parsed
	 * @param strLine
	 */
	private void checkConnection(String strLine){
		String[] tokens = strLine.split(" ");
		//check for connections
		if(tokens[0].equals("CONN")){
			//length of tokens should be 3 for connections
			if(tokens.length == 3){
				String id1 = tokens[1]; //id for first station
				String id2 = tokens[2]; //id for second station
				//check existence for the 2 station in the connection
				//if not exist - then create a "node"
				if(!nodes.containsKey(id1)){
					Node node = new Node(id1, null);
					nodes.put(id1, node);
				}
				if(!nodes.containsKey(id2)){
					Node node = new Node(id2, null);
					nodes.put(id2, node);
				}

				//lexically ordering for ID's on CONN
				int idx = id1.compareTo(id2);
				Node n1 = nodes.get(id1);
				Node n2 = nodes.get(id2);	
				Edge edge = null;
				if(idx > 0){
					edge = new Edge(n2, n1);
					//add edge if not existing
					if(!edges.contains(edge)){
						edges.add(edge);
					}
				}
				if(idx < 0){
					edge = new Edge(n1, n2);
					//add edge if not existing
					if(!edges.contains(edge)){
						edges.add(edge);
					}
				}
				//equal id's detected for connection
				if(idx == 0){
					System.err.println("Detected equal id's for a CONN: " + strLine);
					System.exit(0);
				}
			}else{
				System.err.println("Connection was not not given correctly please correct statement: " + strLine);
				System.exit(0);
			}
		}
	}

	/**
	 * Routes becomes parses, and checks path connectivity (such that there is no jumps in the route) 
	 * @param strLine
	 */
	private void checkRoute(String strLine){
		String[] tokens = strLine.split(" ");
		//if no stations are specified then stop 
		if(tokens.length == 0){
			System.err.println("An error found in the route configuration: " + strLine);
			System.exit(0);
		}
		//if first id on route is stop then break!
		if("stop".equals(tokens[0])){
			System.err.println("stop can not be declared as first node on route: " + strLine);
			System.exit(0);
		}
		ArrayList<Node> route = new ArrayList<Node>();					
		for(int i = 0; i< tokens.length; i++){
			//if stop is detected then insert the last node again
			if(tokens[i].equalsIgnoreCase("stop")){
				Node last = route.get(route.size()-1);
				route.add(last);
			}else{
				Node node = nodes.get(tokens[i]);
				//is node declared in the network configuration?
				if(node == null){
					System.err.println("Node " + tokens[i] + " not declared in network in route: " + strLine);
					System.exit(0);								
				}else{
					route.add(node);
				}
			}
			
			//check reachability for nodes
			if(route.size() >= 2){
				Node n1 = route.get(i-1);
				Node n2 = route.get(i);

				//check reachability for last 2 nodes in if they are different
				if(!n1.getId().equals(n2.getId())){
					//one of those edges should exists - otherwise no reachability!
					Edge e1 = new Edge(n1, n2);
					Edge e2 = new Edge(n2, n1);
					//if none of them exists there is no track between those two nodes!
					if(!edges.contains(e1) && !edges.contains(e2)){
						System.err.println("No tracks between " + n1 + " and " + n2 + ". please check route: " + strLine);
						System.exit(0);
					}
				}
			}
		}
		if(!route.isEmpty()){
			routes.add(route);			
		}
	}
		
	//*** GETTERS ***//
	
	public ArrayList<Node> getNodes() {
		return new ArrayList<Node>(nodes.values());
	}
	
	public ArrayList<Edge> getEdges() {
		return edges;
	}
	
	public ArrayList<ArrayList<Node>> getRoutes() {
		return routes;
	}	
}