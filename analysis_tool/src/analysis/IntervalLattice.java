package analysis;

import java.util.HashMap;
import syntaxtree.DeclarationList;
import syntaxtree.declaration.Declaration;
import syntaxtree.expression.Variable;

/**
 *
 * @author krc
 */
public final class IntervalLattice extends HashMap<Variable, Interval> implements Lattice {

    DeclarationList declarations;
    private IntervalLattice BottomElement = null;
    public final int Maximum = 20;
    public final int Minimum = -10;

    /**
     * Constructor.
     *
     * @param declarations
     */
    public IntervalLattice(DeclarationList declarations) {
        this.declarations = declarations;
        BottomElement = (IntervalLattice) factory();
    }

    private IntervalLattice() {
    }

    @Override
    public Lattice factory() {
        IntervalLattice retval = new IntervalLattice();
        for (Declaration decl : declarations) {
            retval.put(decl.getId(), new Interval(this));
        }
        return retval;
    }

    @Override
    public Lattice iota() {
        IntervalLattice retval = new IntervalLattice();
        for (Declaration decl : declarations) {
            retval.put(decl.getId(), new Interval(this).setAbsoluteInterval(0, 0));
        }
        return retval;
    }

    @Override
    public Lattice BOTTOM() {
        return (Lattice) this.BottomElement;
    }

    @Override
    public boolean subsetOf(Lattice lattice) {
        IntervalLattice sl = (IntervalLattice) lattice;

        // If any key is not a subset of the same key in the other set
        for (Variable key : this.keySet()) {
            Interval currentSet = this.get(key);

            if (!sl.containsKey(key)) {
                return false; // Should throw an exception instead.
            }

            if (!currentSet.subsetOf((Interval) sl.get(key))) {
                return false;

            } 
        }

        return true;

    }

    @Override
    public Lattice union(Lattice lattice) {
        IntervalLattice sl = (IntervalLattice) lattice;

        for (Variable key : this.keySet()) {
            this.get(key).merge(sl.get(key));
        }

        return this;
    }
}
