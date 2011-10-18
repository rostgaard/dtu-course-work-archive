/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;
import play.*;
import play.mvc.*;

import models.Company;
import models.User;
/**
 *
 * @author Kim Rostgaard Christensen
 */
@With(Secure.class)
@CRUD.For(Company.class)
public class Companies extends CRUD {
    @Before
    static void setConnectedUser() {
        if(Security.isConnected()) {
            User user = User.find("byEmail", Security.connected()).first();
            renderArgs.put("user", user.fullname);
            renderArgs.put("company", user.employedAt);
        }
    }
}
