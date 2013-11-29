package syntaxtree.expression;

import analysis.Interval;
import analysis.IntervalLattice;
import analysis.SignSet;
import analysis.SignsLattice;
import flowgraph.datastructure.VariableSet;
import syntaxtree.Type;

/**
 * Data representation for variables
 *
 */
public class Variable extends Expression {

    private Type type;
    private String id;

    @Override
    public SignSet evalulate(SignsLattice lattice) {
        return lattice.lookup(new Variable(type, id));
    }
    
    @Override
    public Interval evalulate(IntervalLattice lattice) {        
        return lattice.lookup(new Variable(type, id));
    }

    public Variable(Type type, String id) {
        this.type = type;
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String debugInformation() {
        return "\nClass: " + getClass().getSimpleName() + "\nType: " + type.toString() + "\nIdentifier: " + id.toString() + "\n";
    }

    @Override
    public String toString() {
        return id;
    }

    @Override
    public VariableSet getVariable() {
        return VariableSet.factory().addVariable(this);
    }

    @Override
    public boolean equals(Object arg) {
        if (arg instanceof Variable) {
            Variable that = (Variable) arg;
            return this.getId().equals(that.getId());
        }

        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;

        for (int i = 0; i < id.length(); i++) {
            hash = hash * 31 + id.charAt(i);
        }

        return hash;
    }
}
