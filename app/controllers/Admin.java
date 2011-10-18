/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;
import play.*;
import play.mvc.*;
 
import java.util.*;
 
import models.*;
/**
 *
 * @author Kim Rostgaard Christensen
 */
@With(Secure.class)
public class Admin extends Controller {
    
    @Before
    static void setConnectedUser() {
        if(Security.isConnected()) {
            User user = User.find("byEmail", Security.connected()).first();
            renderArgs.put("user", user.fullname);
        }
    }

        
public static void index() {
    List<Post> posts = Post.find("author.email", Security.connected()).fetch();
    render(posts);
}

public static void form(Long id) {
    if(id != null) {
        Post post = Post.findById(id);
        render(post);
    }
    render();
}

public static void certificateForm(Long id) {
    if(id != null) {
        Certificate certificate = Certificate.findById(id);
        render(certificate);
    }
    render();
}

public static void save(Long id, String title, String content, String tags) {
    Post post;
    if(id == null) {
        // Create post
        User author = User.find("byEmail", Security.connected()).first();
        post = new Post(author, title, content);
    } else {
        // Retrieve post
        post = Post.findById(id);
        // Edit
        post.title = title;
        post.content = content;
        post.tags.clear();
    }
    // Set tags list
    for(String tag : tags.split("\\s+")) {
        if(tag.trim().length() > 0) {
            post.tags.add(Tag.findOrCreateByName(tag));
        }
    }
    // Validate
    validation.valid(post);
    if(validation.hasErrors()) {
        render("@form", post);
    }
    // Save
    post.save();
    index();
}
    public static void saveCertificate(Long id, String type, String number, Date expirationDate) {
        Certificate certificate;
        if (id == null) {
            // Create post
            User addedBy = User.find("byEmail", Security.connected()).first();
            certificate = new Certificate(type, expirationDate, number, addedBy);
        } else {
            // Retrieve post
            certificate = Certificate.findById(id);
            // Edit
            certificate.type = type;
            certificate.expirationDate = expirationDate;
            certificate.lastModifedBy = User.find("byEmail", Security.connected()).first();
        }
    
        // Validate
        validation.valid(certificate);
        if (validation.hasErrors()) {
            render("@certificateForm", certificate);
        }
        // Save
        certificate.save();
        index();
    }
}
