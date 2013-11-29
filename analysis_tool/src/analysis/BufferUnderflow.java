package analysis;

import syntaxtree.StatementList;
import syntaxtree.Type;
import syntaxtree.expression.Variable;
import flowgraph.datastructure.Node;
import flowgraph.datastructure.VariableSet;

public class BufferUnderflow {

    public static void execute(StatementList stmts, Analysis lattice) {
        for (Node node : stmts.lables()) {
            SignsLattice sl = (SignsLattice) lattice.get(node);
            
            if (node.getStatement().hasPotentialUnderFlow(sl)) {
                System.out.println("Potential underflow detected at label: " + node + " potential values: " + lattice.get(node));
            }
        }
    }

    public static void bufferUnderflow(Node poi, SignsLattice signsLat) {
        VariableSet variables = poi.getStatement().getVariable();
        for (Variable v : variables) {
            if (v.getType() == Type.ARRAY) {
            }
        }
    }
}
