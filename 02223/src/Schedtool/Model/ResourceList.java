package Schedtool.Model;

import java.util.ArrayList;

/**
 *
 * @author Kim Rostgaard Christensen
 */
public class ResourceList extends ArrayList<Resource>{
    
    /**
     *
     * @param name
     * @return
     */
    public Resource find(String name) {
        Resource found = null;
        for(Resource r: this) {
            if(r.getName().equals(name))
                found = r;
        }
        return found;
    }
    
    /**
     *
     * @return
     */
    @Override
    public String toString() {
        String retstr = "";
        for(Resource r: this) {
            retstr += r.getName() + "(" + r.ceiling() +")";
            if(this.indexOf(r) != this.size())
                retstr += ", ";

        }
        return retstr;
    }

}
