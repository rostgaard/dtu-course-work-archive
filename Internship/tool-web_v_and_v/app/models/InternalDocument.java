/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;
import javax.persistence.*;

import play.db.jpa.*;
/**
 *
 * @author Kim Rostgaard Christensen
 */
@Entity
public class InternalDocument extends Model{
    String name;
    String revision;
}
