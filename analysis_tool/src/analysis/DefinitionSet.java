package analysis;

import java.util.Collection;
import java.util.TreeSet;


public class DefinitionSet extends TreeSet<Definition> {

    public void killAll(Collection<Definition> c) {
        DefinitionSet deleted = new DefinitionSet();
        for(Definition d1 : this) {
            for(Definition d2 : c) {
                if (d1.identifier.equals(d2.identifier)) {
                    deleted.add(d1);
                }
            }
        }

        for (Definition definition : deleted) {
            this.remove(definition);
        }
    }


}
