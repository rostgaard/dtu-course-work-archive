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
public class LogEntry extends Model{
    public Date date;
    public String line;
    @ManyToOne
    public User doneBy;
    
    
    public LogEntry(String line, User u) {
        this.doneBy = u;
        this.date = new Date();
        this.line = line;
    }
}
