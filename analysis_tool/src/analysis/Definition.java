/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package analysis;

import flowgraph.datastructure.Node;
import syntaxtree.expression.Expression;
import syntaxtree.expression.Variable;

/**
 *
 * @author krc
 */
public class Definition {

    Variable   identifier = null;
    Node label = null;

    public Definition(Variable var, Node label) {
        this.identifier = var;
        this.label= label;
    }
}
