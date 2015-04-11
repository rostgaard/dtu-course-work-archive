package analysis;

import java.util.Collection;
import java.util.HashSet;

public class DefinitionSet extends HashSet<Definition> {

    public void killAll(Collection<Definition> c) {
        DefinitionSet deleted = new DefinitionSet();
        for (Definition d1 : this) {
            for (Definition d2 : c) {
//                if (d1.identifier.equals(d2.identifier)) {
                deleted.add(d1);
            }
            //          }
        }

        for (Definition definition : deleted) {
            this.remove(definition);
        }
    }

    public boolean subsetOf(DefinitionSet otherSet) {
        for (Definition d : this) {
            if (!otherSet.contains(d)) {
                return false;
            }
        }

        return true;
    }

    public void merge(DefinitionSet otherSet) {
        for (Definition d : otherSet) {
            if (!this.contains(d)) {
                this.add(d);
            }
        }
    }
}
