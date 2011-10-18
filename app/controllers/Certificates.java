/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;
import models.User;
import play.*;
import play.mvc.*;
/**
 *
 * @author Kim Rostgaard Christensen
 */
@With(Secure.class)
public class Certificates extends CRUD {
    @Before
    static void setConnectedUser() {
        if(Security.isConnected()) {
            User user = User.find("byEmail", Security.connected()).first();
            renderArgs.put("user", user.fullname);
            renderArgs.put("company", user.employedAt);
        }
    }
}
