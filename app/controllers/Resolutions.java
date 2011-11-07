/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import models.LogEntry;
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
        if (Security.isConnected()) {
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
        Resolution r = Resolution.find("byDescription", resolution).first();
        if (r == null) {
            r = new Resolution(resolution);
        }

        r.description = resolution;
        r.solutionFor = Requirement.findById(requirement);

        r.save();
        redirect("Requirements.view", r.solutionFor.id);
    }

    public static void accept(long id) {
        Resolution resolution = Resolution.findById(id);
        Requirement requirement = Requirement.findById(resolution.solutionFor.id);
        System.out.println(requirement);
        if (requirement.acceptedResolution == null) {
                requirement.Accept(resolution);
                User user = User.find("byEmail", Security.connected()).first();

                LogEntry entry = new LogEntry("Resolution \"" + resolution + "\" accepted for \""
                        + requirement.activity + "\" " + requirement, user);
                entry.save();
                requirement.save();
        }
        if(!requirement.acceptedResolution.equals(resolution)) {
                requirement.Accept(resolution);
                User user = User.find("byEmail", Security.connected()).first();

                LogEntry entry = new LogEntry("Resolution \"" + resolution + "\" accepted for \""
                        + requirement.activity + "\" " + requirement, user);
                entry.save();
                requirement.save();
            
        }

        redirect("Requirements.view", resolution.solutionFor.id);
    }
}
