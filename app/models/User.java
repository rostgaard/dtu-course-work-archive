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
public class User extends Model {

    @Email
    @Required
    public String email;
    @Required
    public String password;
    public String fullname;
    public String initials;
    public boolean isAdmin;

    public User(String email, String password, String fullname, String initials) {
        this.initials = initials;
        this.email = email;
        this.password = password;
        this.fullname = fullname;
    }

    public static User connect(String email, String password) {
        return find("byEmailAndPassword", email, password).first();
    }
    
    public String toString() {
        return this.fullname;
    }
}
