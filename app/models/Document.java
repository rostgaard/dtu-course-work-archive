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
    @OneToMany
    public Set<Author> authors; // TODO make multiple
    //public String QACertificates;
    public String internalDocument;   
    @Required
    public Date initiationDate;
    @Required
    @OneToOne
    public ProjectPhase projectPhase;
    
    @Required
    public Company owner;
   
    @OneToOne
    public DocumentStatus documentStatus;

    public Document(String title, Set<String> authors, String internalDocument, ProjectPhase projectPhase, DocumentStatus documentStatus) {
        this.title = title;
        this.authors = new TreeSet<Author>();
        this.internalDocument = internalDocument;
        this.projectPhase = projectPhase;
        this.documentStatus = documentStatus;
        this.initiationDate = new Date();
    }

    @Override
    public String toString() {
        return this.title;
    }


}
