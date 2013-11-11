/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package analysis;

import syntaxtree.expression.Expression;
import syntaxtree.expression.Variable;

/**
 *
 * @author krc
 */
public class Definition {

    Variable   identifier = null;
    Expression definition = null;

    public Definition(Variable var, Expression definition) {
        this.identifier = var;
        this.definition = definition;
    }
}
