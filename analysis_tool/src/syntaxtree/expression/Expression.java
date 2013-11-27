package syntaxtree.expression;

import analysis.Lattice;
import analysis.SignSet;
import analysis.SignsLattice;
import flowgraph.datastructure.VariableSet;

/**
 * Abstract class for expressions
 *
 */
public abstract class Expression {

    public abstract VariableSet getVariable();

    public abstract SignSet evalulate(SignsLattice lattice);
}
