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
public class InternalDocument extends models.Document {

    public InternalDocument(String title, String revision) {
        super(title, revision);
    }
    
}
