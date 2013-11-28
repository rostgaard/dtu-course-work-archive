/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package analysis;

import java.util.HashMap;
import syntaxtree.DeclarationList;
import syntaxtree.declaration.Declaration;
import syntaxtree.expression.Variable;

/**
 *
 * @author krc
 */
public final class SignsLattice extends HashMap<Variable, SignSet> implements Lattice {

    DeclarationList declarations;
    private SignsLattice BottomElement = null;

    public SignsLattice(DeclarationList declarations) {
        this.declarations = declarations;
        BottomElement = (SignsLattice) factory();
    }

    private SignsLattice() {
    }

    @Override
    public Lattice factory() {
        SignsLattice retval = new SignsLattice();
        for (Declaration decl : declarations) {
            retval.put(decl.getId(), new SignSet());
        }
        return retval;
    }

    @Override
    public Lattice iota() {
        SignsLattice retval = new SignsLattice();
        for (Declaration decl : declarations) {
            SignSet ss = new SignSet();
            ss.add(Sign.Z);
            retval.put(decl.getId(), ss);
        }
        return retval;
    }

    @Override
    public Lattice BOTTOM() {
        return (Lattice) this.BottomElement;
    }

    @Override
    public boolean subsetOf(Lattice lattice) {
        SignsLattice sl = (SignsLattice) lattice;

        // If any key is not a subset of the same key in the other set
        for (Variable key : this.keySet()) {
            SignSet currentSet = this.get(key);

            if (!sl.containsKey(key)) {
                return false; // Should throw an exception instead.
            }

            if (!currentSet.subsetOf(sl.get(key))) {
                return false;
            }
        }

        return true;

    }

    public SignsLattice clear(Variable var) {
        this.get(var).clear();

        return this;
    }

    @Override
    public Lattice union(Lattice lattice) {
        SignsLattice sl = (SignsLattice) lattice;

        for (Variable key : this.keySet()) {
            this.get(key).merge(sl.get(key));
        }

        return this;
    }
}
