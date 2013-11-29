package analysis;

import flowgraph.datastructure.FlowSet;
import flowgraph.datastructure.Node;
import flowgraph.datastructure.NodeSet;
import flowgraph.datastructure.VariableSet;

/**
 *
 * @author krc
 */
public interface Analysable {
    
    public RDProgramState RD(RDProgramState currentState);

    public abstract NodeSet labels();

    public abstract NodeSet finalNodes();

    public abstract Node initial();

    public abstract FlowSet flow();

    public abstract VariableSet getVariable();

    public abstract boolean hasPotentialUnderFlow(SignsLattice lattice);

}
