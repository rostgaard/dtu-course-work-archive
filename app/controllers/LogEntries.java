/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;
import models.LogEntry;
import play.*;
import play.mvc.*;
import models.User;

/**
 *
 * @author Kim Rostgaard Christensen
 */
@With(Secure.class)
@CRUD.For(LogEntry.class)
public class LogEntries extends CRUD {
    
}
