/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package analysis;

import java.util.ArrayList;
import syntaxtree.declaration.Declaration;
import syntaxtree.expression.Constant;
import syntaxtree.expression.Variable;

/**
 *
 * @author krc
 */
public class RDProgramState {
    private ArrayList<Definition> definitions = new ArrayList<>();
    
    public RDProgramState (ArrayList<Declaration> initialDefinitions) {
        for (Declaration d: initialDefinitions) {
            definitions.add(new Definition(d.getId(), new Constant(0)));
        }
    }
    
    public void kill(Variable var) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void gen(Variable var) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
