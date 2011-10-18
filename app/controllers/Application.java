package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;
import play.data.validation.Required;
import play.data.validation.Validation;

public class Application extends Controller {

    public static void documents() {
        List<Document> documents = Document.findAll();
        render(documents);
    }

    public static void index() {
        Post frontPost = Post.find("order by postedAt desc").first();
        List<Post> olderPosts = Post.find(
                "order by postedAt desc").from(1).fetch(10);
        render(frontPost, olderPosts);
    }

    @Before
    static void addDefaults() {
        renderArgs.put("title", Play.configuration.getProperty("site.title"));
        renderArgs.put("baseline", Play.configuration.getProperty("site.baseline"));
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