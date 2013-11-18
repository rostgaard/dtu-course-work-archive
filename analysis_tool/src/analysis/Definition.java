/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package analysis;

import flowgraph.datastructure.Node;
import syntaxtree.expression.Variable;

/**
 *
 * @author krc
 */
public class Definition {

    Variable identifier = null;
    Node label = null;

    public Definition(Variable var, Node label) {
        this.identifier = var;
        this.label= label;
    }

	@Override
	public boolean equals(Object arg) {
		if(arg instanceof Definition){
			Definition that = (Definition) arg; //type casting
			//if the two identifiers are equals then return true otherwise false is returned
			return this.identifier.getId().equals(that.identifier.getId()) ? true : false; 			
		}
		return false;
	}
}
