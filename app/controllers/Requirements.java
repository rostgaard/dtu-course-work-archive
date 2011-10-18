/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;
import java.util.List;
import models.Activity;
import models.Document;
import models.User;
import play.*;
import play.db.jpa.GenericModel;
import play.mvc.*;
/**
 *
 * @author Kim Rostgaard Christensen
 */
public class Requirements extends Controller{
    @Before
    static void setConnectedUser() {
        if (Security.isConnected()) {
            User user = User.find("byEmail", Security.connected()).first();
            renderArgs.put("user", user.fullname);
            renderArgs.put("company", user.employedAt);
        }
    }
    
    public static void listAll() {
        List<Activity> activities = Activity.all().fetch();
        
        render("@list",activities);
    }
    
    public static void list() {
        List<Document> documents;
    
    }
}
