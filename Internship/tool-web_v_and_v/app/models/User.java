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
public class User extends Model implements Comparable<User> {

    @Email
    @Required
    public String email;
    @Required
    public String password;
    public String fullname;
    public String initials;
    public boolean isAdmin;

    @Required
    @ManyToOne
    public Company employedAt;
    
    
    public User(String email, String password, 
                String fullname, Company employedAt) {
        this.email = email;
        this.password = password;
        this.fullname = fullname;
        this.employedAt = employedAt;
    }
    
    
    public static User connect(String email, String password) {
        return find("byEmailAndPassword", email, password).first();
    }
    
    public String toString() {
        return this.fullname;
    }

    public int compareTo(User o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
