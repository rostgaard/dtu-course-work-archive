/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.dtu.resources.model;

import java.util.HashMap;

/**
 *
 * @author krc
 */
public final class FlightDatabase {
    
    static HashMap<String,FlightInformation> db = new HashMap<String, FlightInformation>();
    
    public static void insert (FlightInformation flight) {
        db.put(flight.getFlightID(), flight);
    }
    
}
