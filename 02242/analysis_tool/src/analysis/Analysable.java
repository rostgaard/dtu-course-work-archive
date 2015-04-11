package analysis;

import analysis.lattices.SignsLattice;
import flowgraph.datastructure.FlowSet;
import flowgraph.datastructure.Node;
import flowgraph.datastructure.NodeSet;
import flowgraph.datastructure.VariableSet;

/**
 * The main interface implemented by statement to enable static analysis on
 * them. This interface dictates which methods are needed for our different
 * analysis'. 
 *
 * @author krc
 */
public interface Analysable {


    public abstract NodeSet labels();

    public abstract NodeSet finalNodes();

    public abstract Node initial();

    public abstract FlowSet flow();

    public abstract VariableSet getVariable();

    public abstract boolean hasPotentialUnderFlow(SignsLattice lattice);
}
