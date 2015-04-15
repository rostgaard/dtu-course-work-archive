/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.*;
import javax.persistence.*;

import play.data.validation.*;
import play.db.jpa.*;

/**
 *
 * @author Kim Rostgaard Christensen
 */
@Entity
public class ProjectPhase extends Model {

    @Required
    public String phaseName;

    public ProjectPhase(String phaseName) {
        this.phaseName = phaseName;
    }

    public String toString() {
        return this.phaseName;
    }
}
