/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;
import java.util.Date;
import models.Certificate;
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
    
    public static void add() {
         render();
    }
    
    public static void addNew(String certificateType, String certificateSerial, Date certificateExpr ) {
          User user = User.find("byEmail", Security.connected()).first();
          Certificate certificate = new Certificate(certificateType, certificateExpr, certificateSerial, user);
          certificate.number = certificateSerial;
          certificate.lastModifedBy = user;
          certificate.belongsTo = user.employedAt;
          System.out.println(certificateSerial);
          certificate.save();
          redirect("Application.index");
    }
}
