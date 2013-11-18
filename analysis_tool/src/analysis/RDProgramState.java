/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package analysis;

import java.util.ArrayList;

import javax.sound.midi.Sequence;

import flowgraph.datastructure.Node;
import syntaxtree.ArithmeticOperation;
import syntaxtree.Type;
import syntaxtree.declaration.Array;
import syntaxtree.declaration.Declaration;
import syntaxtree.declaration.Int;
import syntaxtree.declaration.Level;
import syntaxtree.expression.Constant;
import syntaxtree.expression.Expression;
import syntaxtree.expression.OperationExpression;
import syntaxtree.expression.Variable;
import syntaxtree.statement.Assignment;
import syntaxtree.statement.Statement;
import utilities.Sequencer;

/**
 *
 * @author krc
 */
public class RDProgramState {
   
	private ArrayList<Definition> definitions = new ArrayList<>();
    
    public RDProgramState (ArrayList<Declaration> initialDefinitions) {
        for (Declaration d: initialDefinitions) {
            definitions.add(new Definition(d.getId(), null));
        }
    }
    
    public void kill(Variable var) {
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
    	//remove definitions to be killed
    	definitions.removeAll(kills);
    }

    public void gen(Variable var, Node label) {
    	//create a definition of the variable parameter
    	Definition def = new Definition(var, label);
    	
    	//add to the list
    	definitions.add(def);
    }
    
    //test
    public static void main(String[] args){
    	ArrayList<Declaration> initialDefinitions = new ArrayList<Declaration>();
    	
    	Variable x = new Variable(Type.INT, "x");
    	Variable y = new Variable(Type.INT, "y");
    	Variable z = new Variable(Type.INT, "z");
    	Variable w = new Variable(Type.ARRAY, "w");
    
    	Declaration d1 = new Int(Level.UNKNOWN, x); 
    	Declaration d2 = new Int(Level.UNKNOWN, y); 
    	Declaration d3 = new Int(Level.UNKNOWN, z); 
    	Declaration d4 = new Array(Level.UNKNOWN, w, new Constant(10));
    	
    	initialDefinitions.add(d1);
    	initialDefinitions.add(d2);
    	initialDefinitions.add(d3);
    	initialDefinitions.add(d4);
    	
    	RDProgramState rd = new RDProgramState(initialDefinitions);
    	Sequencer seq = new Sequencer();
    
    	Constant cons7 = new Constant(7);
    	Constant cons2 = new Constant(2);
    	Assignment a1 = new Assignment(x, cons7);
   	    	
    	OperationExpression plus = new OperationExpression(x, cons2, ArithmeticOperation.PLUS);
    	Assignment a2 = new Assignment(y, plus);
    	
    	a1.setLabel(seq);
    	a2.setLabel(seq);
           	
    	Node label1 = new Node(a1);
    	Node label2 = new Node(a2);
    	
    	ArrayList<Definition> definitions = rd.definitions;
    	
    	for(int i = 0; i<definitions.size(); i++){
    		try{
    			System.out.print("("+definitions.get(i).identifier.getId() + ", " + definitions.get(i).label.getLabel() +")");	
    		}catch(NullPointerException e){
    			System.out.print("("+definitions.get(i).identifier.getId() + ", null)");
    		}
    	}
    	System.out.println();
    	
    	rd.kill(x);
    	rd.gen(x, label1);
    	
    	rd.kill(y);
    	rd.gen(y, label2);
    	
    	for(int i = 0; i<definitions.size(); i++){
    		try{
    			System.out.print("("+definitions.get(i).identifier.getId() + ", " + definitions.get(i).label.getLabel() +")");	
    		}catch(NullPointerException e){
    			System.out.print("("+definitions.get(i).identifier.getId() + ", null)");
    		}
    	}
    	System.out.println();
    }    
}
