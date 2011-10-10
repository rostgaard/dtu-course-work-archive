/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;
import java.util.*;
import javax.persistence.*;

import play.data.validation.*;
import play.db.jpa.*;

/**
 *
 * @author Kim Rostgaard Christensen
 */
@Entity
public class Document extends Model{

    @Required
    public String title;
    @Required
    public String revision;
    public String authors; // TODO make multiple
    public String QACertificates;
    public String internalDocument;   
    @Required
    public Date initiationDate;
    @Required
    @OneToOne
    public ProjectPhase projectPhase;
    
    public DocumentStatus documentStatus;

    @OneToMany
    public List<En50126Phase> en50126Phases;

    
    public Document(String title, String revision) {
        this.title = title;
        this.revision = revision;
    }
    
    
    public String toString() {
        return this.title;
    }
}
