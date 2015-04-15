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
public class Author extends Model {
    public String name;
}
