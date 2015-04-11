/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package analysis.lattices;

import analysis.Definition;
import analysis.DefinitionSet;
import flowgraph.datastructure.Node;
import java.util.HashMap;
import syntaxtree.DeclarationList;
import syntaxtree.declaration.Declaration;
import syntaxtree.expression.Variable;

/**
 *
 * @author krc
 */
public final class RDLattice extends HashMap<Variable, DefinitionSet> implements Lattice {

    DeclarationList declarations;
    private RDLattice BottomElement = null;
    
    public RDLattice(DeclarationList declarations) {
        this.declarations = declarations;
        BottomElement = (RDLattice) factory();
    }

    public Lattice get () {
        return null;
        
    }
    
    private RDLattice() {
    }

    @Override
    public Lattice factory() {
        RDLattice retval = new RDLattice();
        for (Declaration decl : declarations) {
            retval.put(decl.getId(), new DefinitionSet());
        }
        return retval;
    }

    public RDLattice kill(Variable var) {
        this.get(var).clear();

        return this;
    }

    public RDLattice gen(Variable var, Node node) {
        
        this.get(var).add(new Definition(node));

        return this;
    }

    @Override
    public Lattice BOTTOM() {
        return (Lattice) this.BottomElement;
    }

    @Override
    public boolean subsetOf(Lattice lattice) {
        RDLattice rl = (RDLattice) lattice;

        // If any key is not a subset of the same key in the other set
        for (Variable key : this.keySet()) {
            DefinitionSet currentSet = this.get(key);

            if (!rl.containsKey(key)) {
                return false; // Should throw an exception instead.
            }

            if (!currentSet.subsetOf(rl.get(key))) {
                return false;
            }
        }

        return true;
    }

    @Override
    public Lattice union(Lattice lattice) {
        RDLattice rdl = (RDLattice) lattice;

        for (Variable key : this.keySet()) {
            this.get(key).merge(rdl.get(key));
        }
        
        return this;
    }

    @Override
    public Lattice iota() {
        return this.factory();
    }
}
