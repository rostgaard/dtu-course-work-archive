package syntaxtree.statement;

import analysis.RDProgramState;
import syntaxtree.Symbols;

/**
 * Data representation for skip statements
 *
 */
public class Skip extends Statement {

    public String debugInformation() {
        return "\nClass: " + getClass().getSimpleName() + "\n";
    }

    @Override
    public String toString() {
        return Symbols.SKIP;
    }

    @Override
    public RDProgramState RD(RDProgramState currentState) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
