/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import jsons.ActivationJSON;
import jsons.CreateResponseJSON;
import jsons.QuestionJSON;
import models.Answer;
import models.Question;
import models.VotingRound;
import play.mvc.Controller;

/**
 *
 * @author veri
 */
public class Management extends Controller {

    private static Gson gson = new Gson();

    public static void getQuestion() {


        // RequestNew message to test:  {"responderID":6547,"pollID":2345}

        BufferedReader reader = new BufferedReader(new InputStreamReader(request.body));
        try {
            String json = reader.readLine();
            QuestionJSON questionMsg = gson.fromJson(json, QuestionJSON.class);

            Question question = questionMsg.makeModelFromJSON();

            // generate data and save question, try until we have unique poll ID
            do {
                question.pollID = new Random(System.currentTimeMillis()).nextInt(999999);
            } while (!Question.find("byPollID", question.pollID).fetch().isEmpty());

            question.adminKey = Question.generateAdminKey(8);
            question.save();

            // retrieve answers from JSON and save them into database
            for (String a : questionMsg.getAnswers()) {
                System.out.println("Answer: " + a);
                new Answer(question, a).save();
            }

//            MailNotifier.sendAdminLink(question);

            renderJSON(new CreateResponseJSON(question.pollID, question.adminKey));

        } catch (IOException ex) {
            ex.printStackTrace();
            renderJSON(new String());
        }

    }

    /**
     * Method for activating the question.
     * URL: <server>/activation/{id}/{adminKey}
     */
    public static void activation() {
        long urlID = params.get("id", Long.class).longValue();
        String adminKey = params.get("adminKey");

        BufferedReader reader = new BufferedReader(new InputStreamReader(request.body));
        try {
            // deserialize json from request body
            String json = reader.readLine();
            ActivationJSON activationMsg = gson.fromJson(json, ActivationJSON.class);

            // retrieve and activate the question
            Question question = Question.find("byPollID", urlID).first();

            if (question == null) {
                renderJSON("The question does not exist!");
            }

            // only when provided adminKey is correct
            if (question.adminKey.equals(adminKey)) {
                question.activateFor(activationMsg.getDuration()).save();
                new VotingRound(activationMsg.getDuration(), question).save();
                renderJSON("activated");
            } else {
                renderJSON("not activated");
            }

        } catch (IOException ex) {
            ex.printStackTrace();
            renderJSON(new String());
        }
    }
}
