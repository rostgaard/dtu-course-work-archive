/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;
import java.util.*;
import javax.persistence.*;

import play.db.jpa.*;
/**
 *
 * @author Kim Rostgaard Christensen
 */
@Entity
public class Activity extends Model {
    public String description;
   
    @OneToOne
    public Company responsible;
    @ManyToMany
    public List<Requirement> requirements;
    
    public String internalDocument;
    public ActivityStatus activityStatus;
    
    public Activity(String description, String en50126Phase, User Responsible) {
        this.description = description;
        this.requirements = new ArrayList<Requirement>();
        this.internalDocument = null;
        this.activityStatus = null;
    }
    
    public String toString() {
        return this.description;
    }
    
}
