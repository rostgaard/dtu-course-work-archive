/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;
import controllers.Security;
import java.util.*;
import javax.persistence.*;

import play.data.validation.Required;
import play.db.jpa.*;
/**
 *
 * @author Kim Rostgaard Christensen
 */
@Entity
public class Certificate extends Model{
    @Required
    public String type; // This could be predefined
    public String number; // Certificate serial number
    @Required
    public Date expirationDate;
    
    @ManyToOne
    public Company belongsTo;

    @ManyToOne
    public User addedBy;
    
    @ManyToOne
    public User lastModifedBy;
    
    public Certificate(String type, Date expirationDate, String number, User addedBy) {
        this.type = type;
        this.expirationDate = expirationDate;
        this.addedBy = addedBy;
    }
    
    public String toString() {
        return this.type + "(SN: "+this.number+")";
    }
    
    
}
