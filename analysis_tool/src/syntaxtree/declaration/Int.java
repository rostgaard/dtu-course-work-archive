package syntaxtree.declaration;

import analysis.Interval;
import analysis.IntervalLattice;
import syntaxtree.expression.Variable;

/**
 * Data representation integer decelerations
 *
 */
public class Int extends Declaration {

    public Int(Level lvl, Variable id) {
        this.lvl = lvl;
        this.id = id;
    }

    @Override
    public String toString() {
        return "\nClass: " + getClass().getSimpleName() + "\nLevel: " + lvl.toString() + "\nType: " + id.getType().toString() + "\nIdentifier: " + id.getId() + "\n";
    }
    
    @Override
    public Interval bounds(IntervalLattice lattice) {
        return (Interval.doubleInfinity(lattice));
    }
    
}
