/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;
import models.Requirement;
import models.Resolution;
import play.*;
import play.mvc.*;
import models.User;
/**
 *
 * @author Kim Rostgaard Christensen
 */
@With(Secure.class)
public class Resolutions extends CRUD {
    @Before
    static void setConnectedUser() {
        if(Security.isConnected()) {
            User user = User.find("byEmail", Security.connected()).first();
            renderArgs.put("user", user.fullname);
            renderArgs.put("company", user.employedAt);
        }
    }
    
    /**
     * TODO refactor this to only use the id. The resolution lookup is pointless
     * @param req_id 
     */
    public static void propose(long req_id) {
        Requirement requirement = Requirement.findById(req_id);
        Resolution resolution = new Resolution("");
        resolution.solutionFor = requirement;
        render(requirement, resolution);
    }
    
    public static void add(Long requirement, String resolution) {
        Resolution r = new Resolution(resolution);
        r.description = resolution;
        r.solutionFor = Requirement.findById(requirement);
       
        r.save();
        redirect("Resolutions.list");
    }
}
