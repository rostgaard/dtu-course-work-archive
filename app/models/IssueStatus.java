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

    private IssueStatus(IssueStatus status) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }

    /**
     * Lazy factory method, looks up an IssueType and creates it, if it does not
     * already exist
     * 
     * @param name The name of the IssueType to lookup
     * @return The found, or a new object with the name.
     */
    public static IssueStatus findOrCreateByName(String name) {
        IssueStatus status = IssueStatus.find("byName", name).first();
        if (status == null) {
            status = new IssueStatus(status);
        }
        return status;
    }
}
