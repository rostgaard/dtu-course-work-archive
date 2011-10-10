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
    String description;
    String en50126Phase;
    User Responsible;
    InternalDocument internalDocument = null;

    public Activity(String description, String en50126Phase, User Responsible) {
        this.description = description;
        this.en50126Phase = en50126Phase;
        this.Responsible = Responsible;
    }
    
    
    
}
