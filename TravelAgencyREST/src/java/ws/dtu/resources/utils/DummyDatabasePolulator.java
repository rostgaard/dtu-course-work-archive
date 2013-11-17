/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.dtu.resources.utils;

import java.util.Date;
import ws.dtu.resources.model.FlightDatabase;
import ws.dtu.resources.model.FlightInformation;
import ws.dtu.resources.model.HotelDatabase;
import ws.dtu.resources.model.HotelInformation;

/**
 *
 * @author krc
 */
public final class DummyDatabasePolulator {
    
    public void createFlightDB() {
        FlightDatabase.insert(new FlightInformation(new Date(), "Kastrup", "Kabul", new Date(), "SAS001"));
        FlightDatabase.insert(new FlightInformation(new Date(), "Kabul", "Kastrup", new Date(), "SAS002"));
        FlightDatabase.insert(new FlightInformation(new Date(), "Kastrup", "Moscow", new Date(), "SAS003"));
        FlightDatabase.insert(new FlightInformation(new Date(), "Kastrup", "Afganistan", new Date(), "SAS004"));
        FlightDatabase.insert(new FlightInformation(new Date(), "Kastrup", "Irak", new Date(), "SAS005"));
        FlightDatabase.insert(new FlightInformation(new Date(), "Kastrup", "Libya", new Date(), "SAS006"));
        FlightDatabase.insert(new FlightInformation(new Date(), "Kastrup", "Kazakhstan", new Date(), "SAS007"));
    }
    
    public void createHotelDB() {
        HotelDatabase.insert(new HotelInformation("Kabul", "Hotel Noisy Neighbours"));
        HotelDatabase.insert(new HotelInformation("Kazakhstan", "The Plaza with cockroaches"));
        HotelDatabase.insert(new HotelInformation("Irak", "At Saddams"));
        HotelDatabase.insert(new HotelInformation("Libia", "At Gaddafi's"));
    }
}
