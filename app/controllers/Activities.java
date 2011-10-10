/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;
import play.*;
import play.mvc.*;

import models.Activity;
/**
 *
 * @author Kim Rostgaard Christensen
 */
@With(Secure.class)
@CRUD.For(Activity.class)
public class Activities extends CRUD {
    
}