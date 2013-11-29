/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.dtu.representation;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author peter
 */
@XmlRootElement
public abstract class Representation {

    private Map<String,Link> l = new HashMap<String, Link>();

    public Representation() {
    }
    
    @XmlElement
    public Link[] getLinks() {
        return l.values().toArray(new Link[0]);
    }
    
    public void setLinks(Link[] links) {
        for (Link link : links) {
            addLink(link);
        }
    }

    public void addLink(Link link) {
        l.put(link.getRelation(), link);
    }
    


    public Link getLinkByRelation(String relation) {
        if (l.containsKey(relation)) {
            return l.get(relation);
        }
        
        return null;
    }
}
