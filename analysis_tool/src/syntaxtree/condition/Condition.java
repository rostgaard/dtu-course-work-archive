package syntaxtree.condition;

import analysis.SignSet;
import analysis.SignsLattice;

/**
 * Abstract class for conditions
 *
 */
public abstract class Condition {
    
    public abstract SignSet evaluate (SignsLattice lattice);

}
