/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package analysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import flowgraph.datastructure.Node;
import syntaxtree.declaration.Declaration;
import syntaxtree.expression.Variable;

/**
 *
 * @author krc
 */
public class RDProgramState {

	private HashMap<Integer, ArrayList<Definition>> RDentry = new HashMap<Integer, ArrayList<Definition>>();
	private HashMap<Integer, ArrayList<Definition>> RDexit = new HashMap<Integer, ArrayList<Definition>>();

	/**
	 * Empty constructor
	 */
	public RDProgramState(){}

	/**
	 * Constructor
	 * @param initialDefinitions
	 */
	public RDProgramState (List<Declaration> initialDefinitions) {
		ArrayList<Definition> definitions = new ArrayList<>();
		for (Declaration d: initialDefinitions) {
			definitions.add(new Definition(d.getId(), null));
		}
		addRDentry(1, definitions);
	}
	
	public void union(ArrayList<Definition> definitions,  HashMap<Integer, ArrayList<Definition>> RDEntry,  HashMap<Integer, ArrayList<Definition>> RDExit){
		for (Map.Entry<Integer, ArrayList<Definition>> entry : RDEntry.entrySet()) {
		    ArrayList<Definition> defs = new ArrayList<Definition>();
			int key = entry.getKey();
		    ArrayList<Definition> value = entry.getValue();
		    
		    defs.addAll(definitions);
		    defs.addAll(value);
		    addRDentry(key, defs);
		}
		
		for (Map.Entry<Integer, ArrayList<Definition>> entry : RDExit.entrySet()) {
		    ArrayList<Definition> defs = new ArrayList<Definition>();
			int key = entry.getKey();
		    ArrayList<Definition> value = entry.getValue();
		    
		    defs.addAll(definitions);
		    defs.addAll(value);
		    addRDexit(key, defs);
		}
	}

	public void addRDentry(int label, ArrayList<Definition> definitions){
		RDentry.put(label, definitions);			
	}

	public void addRDexit(int label, ArrayList<Definition> definitions){
		RDexit.put(label, definitions);
	}

	public ArrayList<Definition> kill(Variable var, ArrayList<Definition> definitions){
		//create a definition of the variable parameter
		Definition def = new Definition(var, null);

		//definitions to be killed
		ArrayList<Definition> kills = new ArrayList<Definition>();

		//find all definitions to be killed
		for(Definition definition : definitions){
			if(definition.equals(def)){
				kills.add(definition);
			}
		}
		return kills;
	}

	public ArrayList<Definition> gen(Variable var, Node label) {
		//create a definition of the variable parameter
		Definition def = new Definition(var, label);

		//add to the list
		ArrayList<Definition> definitions = new ArrayList<Definition>(); 
		definitions.add(def);

		return definitions;
	}

	public ArrayList<Definition> getRDEntry(int label){
		ArrayList<Definition> defs = new ArrayList<Definition>();
		defs.addAll(RDentry.get(label));
		return defs;
	}
	public ArrayList<Definition> getRDExit(int label){
		ArrayList<Definition> defs = new ArrayList<Definition>();
		try{
			defs.addAll((label <= 0) ? RDentry.get(1) : RDexit.get(label));
		}catch(NullPointerException e){
			//defs.addAll(RDentry.get(label));
			return defs;
		}
		return defs;
	}

	public HashMap<Integer, ArrayList<Definition>> getRDentry(){
		return RDentry;	
	}

	public HashMap<Integer, ArrayList<Definition>> getRDexit(){
		return RDexit;	
	}
}
