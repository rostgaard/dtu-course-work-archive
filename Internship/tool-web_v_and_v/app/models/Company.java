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
public class Company extends Model{
    @Required
    public String name;
    
//    @Required
//    public String role;
    
    @OneToMany(mappedBy = "belongsTo", cascade = CascadeType.ALL)
    public List<Certificate> certificates;
    
    
    @OneToMany(mappedBy = "employedAt", cascade = CascadeType.ALL)
    public List<User> Employees;

    public Company(String name, List<Certificate> certificates) {
        this.certificates = new ArrayList<Certificate>();
        this.name = name;
        this.certificates = certificates;
    }

    
    @Override
    public String toString() {
        return this.name;
    }
}
