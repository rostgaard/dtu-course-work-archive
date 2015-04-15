package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;
import play.data.validation.Required;
import play.data.validation.Validation;

@With(Secure.class)
public class Application extends Controller {

//    @Before
//    static void setConnectedUser() {
//        if (Security.isConnected()) {
//            User user = User.find("byEmail", Security.connected()).first();
//            renderArgs.put("user", user.fullname);
//            renderArgs.put("company", user.employedAt);
//            renderArgs.put("title", Play.configuration.getProperty("site.title"));
//            renderArgs.put("baseline", Play.configuration.getProperty("site.baseline"));
//        }
//    }
    public static void documents() {
        List<Document> documents = Document.findAll();
        render(documents);
    }

    public static void index() {
        if (Security.isConnected()) {
            User user = User.find("byEmail", Security.connected()).first();
            List<Certificate> certificates = Certificate.find("byBelongsTo", user.employedAt).fetch();
            List<Document> documents = Document.find("byOwner", user.employedAt).fetch();
            List<Activity> activities = Activity.find("byResponsible", user.employedAt).fetch();

            Stats stats = new Stats();
            
            // Statistics
            for (Activity a : activities) {
                for (Requirement r : a.requirements) {
                    if (r.status.name.equals("Compliant")) {
                        stats.numCompliant++;
                    }
                    else {
                        stats.numNonCompliant++;
                    }

                }
            }

            renderArgs.put("user", user.fullname);
            renderArgs.put("company", user.employedAt);
            renderArgs.put("title", Play.configuration.getProperty("site.title"));
            renderArgs.put("baseline", Play.configuration.getProperty("site.baseline"));

            render(certificates, documents, activities, stats);

        }

    }

    public static void show(Long id) {
        Post post = Post.findById(id);
        render(post);
    }

    public static void postComment(Long postId, @Required String author, @Required String content) {
        Post post = Post.findById(postId);
        if (validation.hasErrors()) {
            render("Application/show.html", post);
        }
        post.addComment(author, content);
        flash.success("Thanks for posting %s", author);
        show(postId);
    }

    public static void listTagged(String tag) {
        List<Post> posts = Post.findTaggedWith(tag);
        render(tag, posts);
    }
}