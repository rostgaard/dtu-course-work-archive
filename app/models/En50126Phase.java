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
public class En50126Phase extends Model {

    @Required
    public String name;
    
    @Required
    public String description;
    
    public En50126Phase(String name, String description) {
        this.name = name;
        this.description = description;
    }
   
    public String toString() {
        return this.name;
    }
    
}
