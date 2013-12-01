/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.dtu.representation;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class Link {

    private String mediaType;
    private String uri;
    private String relation; 

    public Link() {
    }

    public Link(String mediaType, String uri, String relation) {
        this.mediaType = mediaType;
        this.uri = uri;
        this.relation = relation;
    }
    
    public void setURI(String uri) {
        this.uri = uri;
    }

    @XmlAttribute
    public String getURI() {
        return uri;
    }
    
    public void setRelation(String relation) {
        this.relation = relation;
    }

    @XmlAttribute
    public String getRelation() {
        return relation;
    }
    
    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    @XmlAttribute
    public String getMediaType() {
        return mediaType; 
    }

}
