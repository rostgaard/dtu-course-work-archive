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

    Node label = null;
    Object identifier;

    public Definition(Node label) {
        this.label = label;
    }


    @Override
    public boolean equals(Object arg) {
        return this.label == ((Definition) arg).label;
    }

    @Override
    public String toString() {
        try {
            return "" + label;
        } catch (NullPointerException e) {
            return "?";
        }
    }

    @Override
    public int compareTo(Object arg) {
        return this.label.compareTo(((Definition) arg).label);
    }
}
