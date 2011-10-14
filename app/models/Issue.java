/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;
import java.util.*;
import javax.persistence.*;

import play.data.validation.Required;
import play.db.jpa.*;
/**
 *
 * @author Kim Rostgaard Christensen
 */
@Entity
public class Issue extends Model {
    @Required
    public String briefDescription;
    @Required
    public String description;
    @Required
    @OneToOne
    public DocumentRevision documentRevision;
    
    public Date createdAt;
//    @Required
//    @ManyToMany
 //   public List<User> assignees;

    @Required
    @ManyToMany(cascade=CascadeType.PERSIST)
    public Set<User> assignees;
//
    @OneToMany
    public List<Resolution> proposedResolutions;
    
    public Resolution acceptedResolution;

    public Issue(String briefDescription, String description, DocumentRevision documentRevision) {
        this.briefDescription = briefDescription;
        this.description = description;
        this.documentRevision = documentRevision;
        this.assignees = new TreeSet<User>();
        this.createdAt = new Date();
        this.proposedResolutions = new ArrayList<Resolution>();
        //this.assignees = new ArrayList<User>();
    }

    
    
    
    public String toString() {
        return this.briefDescription;
    }
}
