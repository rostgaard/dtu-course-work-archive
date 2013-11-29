package syntaxtree.declaration;

import analysis.Interval;
import analysis.IntervalLattice;
import syntaxtree.expression.Constant;
import syntaxtree.expression.Variable;

/**
 * Data representation array decelerations
 *
 */
public class Array extends Declaration {

    private Constant size;

    public Array(Level lvl, Variable id, Constant size) {
        this.lvl = lvl;
        this.id = id;
        this.size = size;
    }

    public Constant getSize() {
        return size;
    }

    public void setSize(Constant size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "\nClass: " + getClass().getSimpleName() + "\nLevel: " + lvl.toString() + "\nType: " + id.getType().toString() + "\nIdentifier: " + id.getId() + "\nSize: " + size.toString() + "\n";
    }

    @Override
    public Interval bounds(IntervalLattice lattice) {
        return (new Interval(lattice)).setAbsoluteInterval(0, size.getN());
    }
}
