package flowgraph.datastructure;

import java.util.ArrayList;

import syntaxtree.expression.Variable;

public class VariableSet extends ArrayList<Variable> {

    public final static VariableSet emptySet = new VariableSet();

    public static VariableSet factory() {
        return new VariableSet();
    }

    public VariableSet addVariable(Variable v) {
        super.add(v);

        return this;
    }

    /**
     * Horribly inefficient Union operation, please fix prior to actual release
     * upon the world.
     *
     * @param nset
     */
    public VariableSet union(VariableSet vset) {
        for (Variable v : vset) {
            if (!this.contains(v)) {
                this.add(v);
            }
        }

        return this;
    }

    @Override
    public String toString() {
        String buffer = "(";

        for (Variable var : this) {
            buffer += var;
            if (var == this.get(this.size() - 1)) {
            } else {
                buffer += ",";
            }
        }

        return buffer + ")";
    }
}
