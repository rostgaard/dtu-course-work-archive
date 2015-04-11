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

	//special node for 'stop' declarations in the route configuration
	private static Node stopNode = new Node("stop", "stop");

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
			//is any stations/nodes declared in the network configuration file?
			if(nodes.isEmpty()){
				System.err.println("No station/nodes declared in given file ("+filename+")!");
				System.exit(0);
			}
		}catch (Exception e){//Catch exception if any e.g. file not found or file could no be opened
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
			//is any routes declared in the route configuration file?
			if(routes.isEmpty()){
				System.err.println("No routes declared in the given file ("+filename+")!");
				System.exit(0);
			}
		}catch (Exception e){//Catch exception if any e.g. file not found or file could no be opened
			System.err.println(e.getMessage());
			System.exit(0);
		}
	}

	/**
	 * Checks that a station (STAT) is found, if so it becomes parsed
	 * @param strLine - line to be parsed
	 */
	private void checkStation(String strLine){
		String[] tokens = strLine.split(" ");
		//check for stations (STAT) - first token
		if("STAT".equalsIgnoreCase(tokens[0])){
			//length of tokens should be 3 for stations
			if(tokens.length == 3){
				String name = tokens[1]; //name for station
				String id = tokens[2]; //id for station

				//the id of a station must not be 'stop'
				checkStopId(id, strLine);
				//add station if not contained in the set
				checkExistenceOfNode(id, name, strLine, true);
			}else{
				System.err.println("STAT is not syntactically correct, please correct statement: " + strLine);
				System.exit(0);
			}
		}
	}

	/**
	 * Checks that a connection (CONN) is found, if so it becomes parsed
	 * @param strLine - line to be parsed
	 */
	private void checkConnection(String strLine){
		String[] tokens = strLine.split(" ");
		//check for connections - first token
		if("CONN".equals(tokens[0])){
			//length of tokens should be 3 for connections
			if(tokens.length == 3){
				String id1 = tokens[1]; //id for first station
				String id2 = tokens[2]; //id for second station

				//the id of a station/node must not be stop
				checkStopId(id1, strLine);
				checkStopId(id2, strLine);

				//check existence for the 2 station of the connection
				checkExistenceOfNode(id1, null, strLine, false);
				checkExistenceOfNode(id2, null, strLine, false);

				//lexically ordering for id's of the connection
				int idx = id1.compareTo(id2);
				Node n1 = nodes.get(id1);
				Node n2 = nodes.get(id2);	
				if(idx < 0){
					checkExistenceOfEdge(n1, n2, strLine);
				}else if(idx > 0){
					//switch the nodes
					checkExistenceOfEdge(n2, n1, strLine);
				}else{
					//equal id's detected for connection
					System.err.println("Detected equal id's for a CONN, please correct statement: " + strLine);
					System.exit(0);
				}
			}else{
				System.err.println("CONN is not syntactically correct, please correct statement: " + strLine);
				System.exit(0);
			}
		}
	}

	/**
	 * Routes becomes parses, and checks path connectivity (such that there is no jumps in the route)
	 * @param strLine - line to be parsed
	 */
	private void checkRoute(String strLine){
		String[] tokens = strLine.split(" ");
		//if no stations/nodes are specified then stop 
		if(tokens.length == 0){
			System.err.println("No id's found in route: " + strLine);
			System.exit(0);
		}

		ArrayList<Node> route = new ArrayList<Node>();
		for(int i = 0; i < tokens.length; i++){
			if("STOP".equalsIgnoreCase(tokens[i])){
				//if stop is detected as first station in route 
				if(i == 0){
					route.add(stopNode);
				}else{
					//is there any station different than stop declared previously?
					boolean stationExists = false;
					for(int j = i-1; j >= 0; j--){
						Node node = route.get(j);
						if(!stopNode.equals(node)){
							//then add the last declared station to the route
							Node last = route.get(j);
							route.add(last);
							stationExists = true;
							break;
						}
					}
					//no then add the stop node
					if(!stationExists){ 
						route.add(stopNode);
					}
				}
			}else{
				//add node if declared in the network configuration
				Node node = nodes.get(tokens[i]);
				if(node == null){
					System.err.println("Node " + tokens[i] + " has not been declared in the network, please correct statement: " + strLine);
					System.exit(0);								
				}else{
					route.add(node);
				}
			}

			//check connectivity for nodes for last 2 id's in the route configuration
			if(route.size() >= 2){
				Node n1 = route.get(i-1);
				Node n2 = route.get(i);
				checkConnectivity(n1, n2, strLine);
			}
		}
		checkStopRoute(route);
		checkImmediateDirectionChange(route, strLine);
		if(!route.isEmpty()){
			routes.add(route);			
		}
	}

	/**
	 * Checks connectivity between two nodes
	 * @param n1 - node 1 
	 * @param n2 - node 2
	 * @param strLine
	 */
	private void checkConnectivity(Node n1, Node n2, String strLine){
		//check connectivity for nodes in if they are different
		if(!n1.equals(n2)){
			if(!n1.equals(stopNode)){
				//one of those edges should exists - otherwise no connectivity!
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

	/**
	 * Checks immediate direction change of a train.
	 * @param route
	 * @param strLine
	 */
	private void checkImmediateDirectionChange(ArrayList<Node> route, String strLine){
		for(int i = 0; i <= route.size() - 3; i++){
			Node curr = route.get(i);
			Node next1 = route.get(i+1);
			Node next2 = route.get(i+2);

			//error detected when curr and next2 station is equal and curr and next station is different!
			if(curr.equals(next2) && !curr.getId().equals(next1.getId())){
				System.err.println("Immediate train direction change detected on route: " + strLine);
				System.err.println("Error found in: " + curr.getId() + " " + next1.getId() + " " + next2.getId());
				System.exit(0);
			}
		}
	}

	/**
	 * Checks whether the route only consist of "stops"
	 * @param route
	 */
	private void checkStopRoute(ArrayList<Node> route){
		for(Node node : route){
			if(!node.equals(stopNode)){
				return;				
			}
		}
		System.err.println("Found a route with only stops!");
		System.exit(0);
	}

	/**
	 * Checks that the id of the parsed station/node is not stop
	 * @param id - id of the station
	 * @param strLine
	 */
	private void checkStopId(String id, String strLine){
		//the id of a station/node must not be stop
		if("STOP".equalsIgnoreCase(id)){
			System.err.println("Id of station/node cannot be \'stop\', please correct statement: " + strLine);
			System.exit(0);
		}
	}

	/**
	 * Check existence of a node/station - if declared twice then warn the user
	 * @param id - id of the node/station
	 * @param name - name of the station
	 * @param strLine
	 * @param doublets - look for doublets?
	 */
	private void checkExistenceOfNode(String id, String name, String strLine, boolean doublets){
		//add node/station if not contained in the set
		if(!nodes.containsKey(id)){
			Node node = new Node(id, name);
			nodes.put(id, node);
		}else{
			if(doublets){
				//Doublets found!
				System.err.println("The station/node has been declared before, please correct statement: " + strLine);
				System.exit(0);
			}
		}
	}

	/**
	 * Check existence of a edge - if declared twice then warn the user
	 * @param n1 - first node (source)
	 * @param n2 - second node (target)
	 * @param strLine
	 */
	private void checkExistenceOfEdge(Node n1, Node n2, String strLine){
		Edge edge = new Edge(n1, n2);
		//add edge if not existing
		if(!edges.contains(edge)){
			edges.add(edge);
		}else{
			//Doublets found!
			System.err.println("The CONN has been declared before, please correct statement: " + strLine);
			System.exit(0);
		}
	}

	//*** GETTERS ***//

	/**
	 * Returns the stations/nodes read from the network configuration file
	 * @return - list of stations/nodes
	 */
	public ArrayList<Node> getNodes() {
		return new ArrayList<Node>(nodes.values());
	}

	/**
	 * Returns the tracks read from the network configuration file
	 * @return - list of tracks
	 */
	public ArrayList<Edge> getEdges() {
		return edges;
	}

	/**
	 * Returns the routes read from the route configuration file
	 * @return - routes mapped in a double array
	 */
	public ArrayList<ArrayList<Node>> getRoutes() {
		return routes;
	}	
}
