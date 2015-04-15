/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.List;
import models.DocumentRevision;
import models.Issue;
import models.User;
import play.*;
import play.db.jpa.GenericModel;
import play.mvc.*;

/**
 *
 * @author Kim Rostgaard Christensen
 */
@With(Secure.class)
public class Issues extends Controller {

    @Before
    static void setConnectedUser() {
        if (Security.isConnected()) {
            User user = User.find("byEmail", Security.connected()).first();
            renderArgs.put("user", user.fullname);
            renderArgs.put("company", user.employedAt);
        }
    }

    public static void raise(Long rid) {
        DocumentRevision documentRevision = DocumentRevision.findById(rid);
        User user = User.find("byEmail", Security.connected()).first();

        render(documentRevision);

    }
    
    public static void listAll() {
        List<Issue> issues = Issue.all().fetch();
        render(issues);
    }
    
    public static void show(Long id) {
        Issue issue = Issue.findById(id);
        render(issue);
    }  


    public static void save(Long id, String briefDescription, String description, Long documentRevisionId) {
        Issue issue;
        if (id == null) {
            // Create post
            User createdBy = User.find("byEmail", Security.connected()).first();
            DocumentRevision documentRevision = DocumentRevision.findById(documentRevisionId);
            
            issue = new Issue(briefDescription, description, documentRevision);
        } else {
            // Retrieve issue
            issue = Issue.findById(id);
            // Edit
            issue.briefDescription = briefDescription;
            issue.description = description;
        }
        // Validate
        validation.valid(issue);
        if (validation.hasErrors()) {
            render("@raise", issue);
        }
        // Save
        issue.save();
    }
}
