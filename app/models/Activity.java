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
    public List<En50126Phase> en50126Phases;
    
    InternalDocument internalDocument;

    public Activity(String description, String en50126Phase, User Responsible) {
        this.description = description;
        this.en50126Phases = new ArrayList<En50126Phase>();
        this.internalDocument = null;
    }
    
    public String toString() {
        return this.description;
    }
    
}
