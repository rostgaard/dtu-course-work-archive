package flowgraph.datastructure;

import java.util.ArrayList;

import syntaxtree.expression.Variable;

public class VariableSet extends ArrayList<Variable> {

    public final static VariableSet emptySet = new VariableSet();

    public static VariableSet factory() {
        return new VariableSet();
    }

    /**
     * Chaining version of add. Enables us to chain procedure on a set, instead
     * of having instantiate an object and push-by-procedure.
     *
     * @param variable The new variable to push to the set.
     * @return The reference to the same object.
     */
    public VariableSet addVariable(Variable variable) {
        super.add(variable);

        return this;
    }

    /**
     * Horribly inefficient Union operation. Please fix prior to actual release
     * upon the world.
     *
     * @param otherSet The rhs of the union operation.
     */
    public VariableSet union(VariableSet otherSet) {
        for (Variable v : otherSet) {
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
