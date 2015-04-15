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
    @OneToMany(mappedBy = "activity", cascade = CascadeType.ALL)
    public List<Requirement> requirements;
    
    //public String internalDocument;

    public Activity(String description, Company responsible, List<Requirement> requirements) {
        this.description = description;
        this.responsible = responsible;
        this.requirements = requirements;
    }
    
    
    public String toString() {
        return this.description;
    }

    
    public ActivityStatus status() {
        // TODO Loop through every requirement to determine the status
        return new ActivityStatus();
    }
}
