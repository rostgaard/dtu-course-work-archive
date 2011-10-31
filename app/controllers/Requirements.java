/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.List;
import models.Activity;
import models.Document;
import models.Requirement;
import models.User;
import play.*;
import play.db.jpa.GenericModel;
import play.mvc.*;

/**
 *
 * @author Kim Rostgaard Christensen
 */
public class Requirements extends CRUD {

    @Before
    static void setConnectedUser() {
        if (Security.isConnected()) {
            User user = User.find("byEmail", Security.connected()).first();
            renderArgs.put("user", user.fullname);
            renderArgs.put("company", user.employedAt);
        }
    }

    
   
    public static void add(long id) {
        Activity activity = Activity.findById(id);
        Requirement object = new Requirement();
        object.activity = activity;
                
        
        render(object,activity);
    }
}
