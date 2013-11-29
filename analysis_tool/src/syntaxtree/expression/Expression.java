package syntaxtree.expression;

import analysis.Interval;
import analysis.IntervalLattice;
import analysis.OutOfBoundsException;
import analysis.SignSet;
import analysis.SignsLattice;
import analysis.UnderFlowException;
import flowgraph.datastructure.VariableSet;

/**
 * Abstract class for expressions, giving out information about the signature of
 * what we can expect from a derived expression.
 *
 */
public abstract class Expression {

    /**
     * Checks if given expression is within its bounds. Used for asserting array
     * access never has an out-of-bounds values.
     *
     * @param lattice The definitions reached at the label.
     * @throws OutOfBoundsException
     */
    public void checkBounds(IntervalLattice lattice) throws OutOfBoundsException {
        /*
         * Just move along, nothing to see here.
         * All the good stuff happens in the overridden methods anyway.
         * 
         * This method is only here because the programmer is lazy, and did
         * no felt like overridding methods for every statement.
         */
    }

    /**
     * Checks if given expression does not underflow. Used for asserting array
     * access is not done by a negative index.
     *
     * @param lattice The definitions reached at the label.
     * @throws UnderFlowException
     */
    public void checkUnderflow(SignsLattice lattice) throws UnderFlowException {
        /*
         * Just move along, nothing to see here.
         * All the good stuff happens in the overridden methods anyway.
         * 
         * This method is only here because the programmer is lazy, and did
         * no felt like overridding methods for every statement.
         */
    }

    /**
     * Harvests all the variables used within the expression.
     *
     * @return The set of variables that are contained within the expression
     */
    public abstract VariableSet getVariable();

    /**
     * Evaluates the expression, with regards to signs, given the definitions
     * reached so far.
     *
     * @param lattice The definitions reached at the label.
     * @return A set of signs potentially resulting from this expression.
     */
    public abstract SignSet evalulate(SignsLattice lattice);

    /**
     * Evaluates the expression, with regards to intervals, given the
     * definitions reached so far.
     *
     * @param lattice The definitions reached at the label.
     * @return A in interval potentially resulting from this expression.
     */
    public abstract Interval evalulate(IntervalLattice lattice);
}
