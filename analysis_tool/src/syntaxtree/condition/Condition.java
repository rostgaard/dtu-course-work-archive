package syntaxtree.condition;

import analysis.SignSet;
import analysis.SignsLattice;

/**
 * Abstract class for conditions
 *
 */
public abstract class Condition {
    
    public abstract void evaluate (SignsLattice lattice, Boolean trueBranch);
    public abstract boolean hasPotentialUnderFlow(SignsLattice lattice);

}
