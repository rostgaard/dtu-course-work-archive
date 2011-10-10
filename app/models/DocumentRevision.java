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
public class DocumentRevision extends Model{
    @Required
    @OneToOne
    public Document originDocument;
    public String name;
    
    public String toString() {
        return this.originDocument.title + " revision: "+this.name;
    }
    
}
