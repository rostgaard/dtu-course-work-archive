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
public class Requirement extends Model{
    @Required
    @ManyToOne
    public En50126Phase en50126Phase;
    //public String description;
    @ManyToOne
    public RequirementStatus status;
    @Required
    @ManyToOne
    public Activity activity;
    
    
    @Override
    public String toString() {
        return "("+this.en50126Phase +")";
    }
}
