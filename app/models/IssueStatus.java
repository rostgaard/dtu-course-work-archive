/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;
import javax.persistence.*;

import play.data.validation.Required;
import play.db.jpa.*;
/**
 *
 * @author Kim Rostgaard Christensen
 */
@Entity
public class IssueStatus extends Model {
    public String name;
   
    public String toString() {
        return this.name;
    }
}
