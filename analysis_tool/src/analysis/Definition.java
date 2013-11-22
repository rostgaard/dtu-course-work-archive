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
			Boolean equalIdentifiers = this.identifier.getId().equals(that.identifier.getId());

            Boolean equalLabels = false;
            if (this.label==null && that.label==null) {
                equalLabels = true;
            }else if (this.label!=null && that.label!=null) {
                equalLabels = this.label.getLabel()==that.label.getLabel();
            }

            return equalIdentifiers && equalLabels;
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

            int identifierCompare =  this.identifier.getId().compareTo(that.identifier.getId());
            if (identifierCompare!=0) {
                return identifierCompare;
            }else {
                if (this.label==null && that.label==null) {
                    return 0;
                }else if (this.label!=null && that.label!=null) {
                    return this.label.compareTo(that.label);
                }else if (that.label==null) {
                    return 1;
                }else if (this.label==null) {
                    return -1;
                }
            }
        }

        return -1;
    }
}
