/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Simulator;

import java.util.ArrayList;

/**
 *
 * @author krc
 */
public class ResourceList extends ArrayList<Resource>{
    
    public Resource find(String name) {
        Resource found = null;
        for(Resource r: this) {
            if(r.getName().equals(name))
                found = r;
        }
        return found;
    }
    
    @Override
    public String toString() {
        String retstr = "";
        for(Resource r: this) {
            retstr += r.getName();
            if(this.indexOf(r) != this.size())
                retstr += ", ";
        };
        return retstr;
    }

}
