/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package analysis;

import java.util.*;

import flowgraph.datastructure.Node;
import syntaxtree.declaration.Declaration;
import syntaxtree.expression.Variable;

/**
 *
 * @author krc
 */
public class RDProgramState {

	private HashMap<Integer, TreeSet<Definition>> RDentry = new HashMap<Integer, TreeSet<Definition>>();
	private HashMap<Integer, TreeSet<Definition>> RDexit = new HashMap<Integer, TreeSet<Definition>>();

	/**
	 * Empty constructor
	 */
	public RDProgramState(){}

	/**
	 * Constructor
	 * @param initialDefinitions
	 */
	public RDProgramState (List<Declaration> initialDefinitions) {
        TreeSet<Definition> definitions = new TreeSet<>();
		for (Declaration d: initialDefinitions) {
			definitions.add(new Definition(d.getId(), null));
		}
		addRDentry(1, definitions);
	}
	
	public void union(ArrayList<Definition> definitions,  HashMap<Integer, ArrayList<Definition>> RDEntry,  HashMap<Integer, ArrayList<Definition>> RDExit){
		for (Map.Entry<Integer, ArrayList<Definition>> entry : RDEntry.entrySet()) {
            TreeSet<Definition> defs = new TreeSet<Definition>();
			int key = entry.getKey();
		    ArrayList<Definition> value = entry.getValue();
		    
		    defs.addAll(definitions);
		    defs.addAll(value);
		    addRDentry(key, defs);
		}
		
		for (Map.Entry<Integer, ArrayList<Definition>> entry : RDExit.entrySet()) {
            TreeSet<Definition> defs = new TreeSet<Definition>();
			int key = entry.getKey();
		    ArrayList<Definition> value = entry.getValue();
		    
		    defs.addAll(definitions);
		    defs.addAll(value);
		    addRDexit(key, defs);
		}
	}

	public void addRDentry(int label, TreeSet<Definition> definitions){
		RDentry.put(label, definitions);			
	}

	public void addRDexit(int label, TreeSet<Definition> definitions){
		RDexit.put(label, definitions);
	}

	public TreeSet<Definition> kill(Variable var, TreeSet<Definition> definitions){
		//create a definition of the variable parameter
		Definition def = new Definition(var, null);

		//definitions to be killed
        TreeSet<Definition> kills = new TreeSet<Definition>();

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

	public TreeSet<Definition> getRDEntry(int label){
        TreeSet<Definition> defs = new TreeSet<Definition>();
		defs.addAll(RDentry.get(label));
		return defs;
	}
	public TreeSet<Definition> getRDExit(int label){
        TreeSet<Definition> defs = new TreeSet<>();
		try{
			defs.addAll((label <= 0) ? RDentry.get(1) : RDexit.get(label));
		}catch(NullPointerException e){
			//defs.addAll(RDentry.get(label));
			return defs;
		}
		return defs;
	}

	public HashMap<Integer, TreeSet<Definition>> getRDentry(){
		return RDentry;	
	}

	public HashMap<Integer, TreeSet<Definition>> getRDexit(){
		return RDexit;	
	}
}
