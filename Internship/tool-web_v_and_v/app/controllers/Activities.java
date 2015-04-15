/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;
import play.*;
import play.mvc.*;

import models.Activity;
import models.User;
/**
 *
 * @author Kim Rostgaard Christensen
 */
@With(Secure.class)
@CRUD.For(Activity.class)
public class Activities extends CRUD {
    @Before
    static void setConnectedUser() {
        if(Security.isConnected()) {
            User user = User.find("byEmail", Security.connected()).first();
            renderArgs.put("user", user.fullname);
            renderArgs.put("company", user.employedAt);
        }
    }
}