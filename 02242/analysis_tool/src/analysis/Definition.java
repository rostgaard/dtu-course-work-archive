package analysis;

import flowgraph.datastructure.Node;

/**
 * A definition used in Reaching Definitions analysis. Is merely a holder class
 * between DefinitionSet and Node, and could probably be replaced by Node if we
 * can rid the identifier field.
 *
 * @author krc
 */
public class Definition implements Comparable {

    Node label = null;
    Object identifier;

    public Definition(Node label) {
        this.label = label;
    }

    /**
     *
     * @param The object
     * @return A string representation of the object.
     */
    @Override
    public boolean equals(Object otherDefinition) {
        if (otherDefinition instanceof Definition) {
            return this.label.getLabel() == ((Definition) otherDefinition).label.getLabel();
        }
        return false;
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

    @Override
    public int hashCode() {
        return this.label.getLabel();
    }
}
