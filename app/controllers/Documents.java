/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import models.Document;
import models.DocumentRevision;
import models.Issue;
import models.ProjectPhase;
import models.User;
import play.*;
import play.db.jpa.GenericModel;
import play.mvc.*;

/**
 *
 * @author Kim Rostgaard Christensen
 */
@With(Secure.class)
public class Documents extends CRUD{

    @Before
    static void setConnectedUser() {
        if (Security.isConnected()) {
            User user = User.find("byEmail", Security.connected()).first();
            renderArgs.put("user", user.fullname);
            renderArgs.put("company", user.employedAt);
        }
    }

    public static void byPhase(String phase) {
        List<Document> documents;
        // Catchall
        if (phase.equals("all")) {
            documents = Document.all().fetch();
        } else {
            ProjectPhase projectPhase = ProjectPhase.find("byPhaseName", phase).first();
            documents = Document.find("byProjectPhase", projectPhase).fetch();
        }

        render(documents, phase);
    }

    public static void add() {
        render();
    }
    
    public static void show(Long id) {
        Document document = Document.findById(id);
        List<DocumentRevision> revisions = DocumentRevision.find("byOriginDocument",document).fetch();
        List<Issue> issues = new ArrayList<Issue>();
        
        for(DocumentRevision revision : revisions) {
            List<Issue> ilist = Issue.find("byDocumentRevision", revision).fetch();
            issues.addAll(ilist);
        }

       render(document,revisions, issues);
    }  
    
    
    public static void listAll() {
        List<Document> documents = Document.all().fetch();
        render(documents);
    }
    
        
    public static void save(Long id, String briefDescription, String description, Long documentRevisionId) {
        description = null;
    }
    
}
