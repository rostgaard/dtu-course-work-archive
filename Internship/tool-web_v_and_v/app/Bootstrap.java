/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import play.*;
import play.jobs.*;
import play.test.*;
 
import models.*;

/**
 *
 * @author Kim Rostgaard Christensen
 */
 
@OnApplicationStart
public class Bootstrap extends Job {
 
    public void doJob() {
        // Check if the database is empty
        if(Document.count() == 0) {
            Fixtures.loadModels("initial-data.yml");
        }
    }
 
}