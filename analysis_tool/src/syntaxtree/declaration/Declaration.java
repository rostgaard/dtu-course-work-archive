package syntaxtree.declaration;

import analysis.Interval;
import analysis.IntervalLattice;
import flowgraph.datastructure.VariableSet;
import syntaxtree.expression.Variable;

/**
 * Abstract class for declarations
 *
 */
public abstract class Declaration implements Comparable<Declaration> {

    protected Level lvl;
    protected Variable id;

    public Level getLvl() {
        return lvl;
    }

    public void setLvl(Level lvl) {
        this.lvl = lvl;
    }

    public Variable getId() {
        return id;
    }

    public void setId(Variable id) {
        this.id = id;
    }

    public VariableSet getVariable() {
        return VariableSet.factory().addVariable(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Declaration)) {
            return false;
        }
        return this.id.equals(((Declaration)obj).id);
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public int compareTo(Declaration t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public abstract Interval bounds(IntervalLattice lattice);
}
