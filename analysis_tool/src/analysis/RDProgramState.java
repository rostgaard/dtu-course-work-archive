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
public final class RDProgramState extends ProgramState {

    private HashMap<Integer, DefinitionSet> RDentry = new HashMap<>();
    private HashMap<Integer, DefinitionSet> RDexit = new HashMap<>();

    /**
     * Empty constructor
     */
    public RDProgramState() {
    }

    /**
     * Constructor
     *
     * @param initialDefinitions
     */
    public RDProgramState(List<Declaration> initialDefinitions) {
        DefinitionSet definitions = new DefinitionSet();
        for (Declaration d : initialDefinitions) {
//			definitions.add(new Definition(d.getId(), null));
        }
        addRDentry(1, definitions);
    }

    public void union(ArrayList<Definition> definitions, HashMap<Integer, DefinitionSet> RDEntry, HashMap<Integer, DefinitionSet> RDExit) {
        for (Map.Entry<Integer, DefinitionSet> entry : RDEntry.entrySet()) {
            DefinitionSet defs = new DefinitionSet();
            int key = entry.getKey();
            DefinitionSet value = entry.getValue();

            defs.addAll(definitions);
            defs.addAll(value);
            addRDentry(key, defs);
        }

        for (Map.Entry<Integer, DefinitionSet> entry : RDExit.entrySet()) {
            DefinitionSet defs = new DefinitionSet();
            int key = entry.getKey();
            DefinitionSet value = entry.getValue();

            defs.addAll(definitions);
            defs.addAll(value);
            addRDexit(key, defs);
        }
    }

    public void addRDentry(int label, DefinitionSet definitions) {
        RDentry.put(label, definitions);
    }

    public void addRDexit(int label, DefinitionSet definitions) {
        RDexit.put(label, definitions);
    }

    public DefinitionSet kill(Variable var, DefinitionSet definitions) {
        //create a definition of the variable parameter
        Definition def = null; //new Definition(var, null);

        //definitions to be killed
        DefinitionSet kills = new DefinitionSet();

        //find all definitions to be killed
        for (Definition definition : definitions) {
            if (definition.identifier.equals(def.identifier)) {
                kills.add(definition);
            }
        }
        return kills;
    }

    public DefinitionSet gen(Variable var, Node label) {
        //create a definition of the variable parameter
        Definition def = null; //new Definition(var, label);

        //add to the list
        DefinitionSet definitions = new DefinitionSet();
        definitions.add(def);

        return definitions;
    }

    public DefinitionSet getRDEntry(int label) {
        DefinitionSet defs = new DefinitionSet();
        defs.addAll(RDentry.get(label));
        return defs;
    }

    public DefinitionSet getRDExit(int label) {
        DefinitionSet defs = new DefinitionSet();
        try {
            defs.addAll((label <= 0) ? RDentry.get(1) : RDexit.get(label));
        } catch (NullPointerException e) {
            //defs.addAll(RDentry.get(label));
            return defs;
        }
        return defs;
    }

    public HashMap<Integer, DefinitionSet> getRDentry() {
        return RDentry;
    }

    public HashMap<Integer, DefinitionSet> getRDexit() {
        return RDexit;
    }
}
