/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ws.travelagency.representation;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Andreas
 */
@XmlRootElement
public class Representation {

    ArrayList<Link> links = new ArrayList<Link>();

    @XmlElement
    public ArrayList<Link> getLinks() {
        return links;
    }

    public void addLink(Link link) {
        links.add(link);
    }

    public Link getLinkByName(String link) {
        Link returnLink = null;

        for (Link relLink : links) {
           if (relLink.getRel().toLowerCase().
                   equals(link.toLowerCase())) {
               returnLink = relLink;
               break;
           }
        }
        return returnLink;

    }
}
