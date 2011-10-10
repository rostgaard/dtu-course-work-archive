package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends Controller {
   
    public static void index() {
        List<Document> documents = Document.findAll();
        render(documents);
    }

    @Before
    static void addDefaults() {
        renderArgs.put("title", Play.configuration.getProperty("site.title"));
        renderArgs.put("baseline", Play.configuration.getProperty("site.baseline"));
    }

    public static void show(Long id) {
        Document document = Document.findById(id);
        render(document);
    }
}