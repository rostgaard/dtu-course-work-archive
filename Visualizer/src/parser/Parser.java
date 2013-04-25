package parser;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

import domain.Edge;
import domain.Node;

public class Parser {

	private HashMap<String, Node> nodes;
	private ArrayList<Edge> edges;	
	private ArrayList<ArrayList<Node>> routes;

	public Parser(){
		this.nodes = new HashMap<String, Node>();
		this.edges = new ArrayList<Edge>();
		this.routes = new ArrayList<ArrayList<Node>>();
	}

	private void checkStation(String strLine){
		String[] tokens = strLine.split(" ");
		//check for stations
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

	private void checkConnection(String strLine){
		String[] tokens = strLine.split(" ");
		//check for connections
		if(tokens[0].equals("CONN")){
			//length of tokens should be 3
			if(tokens.length == 3){
				String id1 = tokens[1];
				String id2 = tokens[2];
				//check existence of the "station" in the connection
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
				int i = id1.compareTo(id2);
				Node n1 = nodes.get(id1);
				Node n2 = nodes.get(id2);	
				Edge edge = null;
				if(i > 0){
					edge = new Edge(n2, n1);
					//add edge if not existing
					if(!edges.contains(edge)){
						edges.add(edge);
					}
				}
				if(i < 0){
					edge = new Edge(n1, n2);
					//add edge if not existing
					if(!edges.contains(edge)){
						edges.add(edge);
					}
				}
				if(i == 0){
					System.err.println("Detected equal id's for a CONN: " + strLine);
					System.exit(0);
				}
			}else{
				System.err.println("Connection was not not given correctly please correct statement: " + strLine);
				System.exit(0);
			}
		}
	}

	private void checkNetwork(){
		if(nodes.isEmpty()){
			System.err.println("No station/nodes found declared");
			System.exit(0);
		}
	}
	
	private void checkRoute(String strLine){
		String[] tokens = strLine.split(" ");

		//if first id on route is stop then stop!
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
					Edge e1 = new Edge(n1, n2);
					Edge e2 = new Edge(n2, n1);
					//one of those edges should exists - otherwise no reachability!
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

	public void readNetwork(String filename){
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
			checkNetwork();
		}catch (Exception e){//Catch exception if any
			System.err.println(e.getMessage());
			System.exit(0);
		}

	}

	public void readRutes(String filename){
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
		}catch (Exception e){//Catch exception if any
			System.err.println(e.getMessage());
			System.exit(0);
		}
	}

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