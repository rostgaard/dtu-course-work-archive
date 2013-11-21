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
public class Definition implements Comparable {

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
			return this.identifier.getId().equals(that.identifier.getId());
		}
		return false;
	}
	
	@Override
	public String toString(){
		try{
			return "("+identifier.getId() + ", " + label.getLabel() +")";	
		}catch(NullPointerException e){
			return "("+identifier.getId() + ", null)";
		}
	}

    @Override
    public int compareTo(Object arg) {
        if(arg instanceof Definition){
            Definition that = (Definition) arg; //type casting

            if (this.label==null) {
                return -1;
            }

            if (that.label==null) {
                return 1;
            }

            Integer thisLabel = this.label.getLabel();
            Integer thatLabel = that.label.getLabel();

            if (thisLabel==thatLabel) {
                return this.identifier.getId().compareTo(that.identifier.getId());
            }else if(thisLabel>thatLabel) {
                return 1;
            }else {
                return -1;
            }
        }

        return -1;
    }
}
