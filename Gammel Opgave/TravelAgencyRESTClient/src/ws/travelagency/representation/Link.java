/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ws.travelagency.representation;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Andreas
 */
@XmlRootElement
public class Link {

    String mediaType;
    String uri;
    String rel; 

    public Link() {

    }

    
    public void setURI(String uri) {
        this.uri = uri;
    }

    @XmlAttribute
    public String getURI() {
        return uri;
    }
    
    public void setRel(String rel) {
        this.rel = rel;
    }

    @XmlAttribute
    public String getRel() {
        return rel;
    }
    
    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    @XmlAttribute
    public String getMediaType() {
        return mediaType; 
    }

}
