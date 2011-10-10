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
    public String description;
    @Required
    @OneToOne
    public DocumentRevision documentRevision;
    
    @Required
    @OneToMany
    public List<User> assignees;

    @Required
    @OneToMany
    public List<User> QAResponsibles;

    @OneToMany
    public List<Resolution> proposedResolutions;
    
    public Resolution acceptedResolution;
    
    public String toString() {
        return this.description;
    }
}
