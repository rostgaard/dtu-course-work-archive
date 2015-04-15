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
public class Resolution extends Model {
    public String description;
    
    @ManyToOne
    public Requirement solutionFor;

    @Override
    public String toString() {
        return this.description;
    }

    public Resolution(String description) {
        this.description = description;
    }
    
    public void Accept() {
        this.solutionFor.Accept(this);
    }

}
