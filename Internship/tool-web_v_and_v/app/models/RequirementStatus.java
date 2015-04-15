/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.awt.Color;
import javax.persistence.*;

import play.data.validation.Required;
import play.db.jpa.*;

/**
 *
 * @author Kim Rostgaard Christensen
 */
@Entity
public class RequirementStatus extends Model {

    public String css_class;
    public String name = "";

    private RequirementStatus(RequirementStatus status) {
        this.name = "";
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
    public static RequirementStatus findOrCreateByName(String name) {
        RequirementStatus status = RequirementStatus.find("byName", name).first();
        if (status == null) {
            status = new RequirementStatus(status);
        }
        return status;
    }
}
