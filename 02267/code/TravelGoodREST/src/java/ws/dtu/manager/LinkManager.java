/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.dtu.manager;

import ws.dtu.model.Itinerary;
import ws.dtu.representation.Link;
import ws.dtu.representation.Representation;

/**
 * 
 * @author peter
 */
public class LinkManager {
    
    private static final String MEDIATYPE = "application/itinerary+xml";
    
    protected static final String BASE_RELATION = "http://itinerary.ws/relations/";
    protected static final String BASE_URL = "http://localhost:8080/ta/webresources/itinerary/";

    protected static final String SELF_RELATION = BASE_RELATION + "self";
    protected static final String REMOVE_RELATION = BASE_RELATION + "remove";
    protected static final String CANCEL_RELATION = BASE_RELATION + "cancel";
    protected static final String BOOKING_RELATION = BASE_RELATION + "booking";
    protected static final String FLIGHT_RELATION = BASE_RELATION + "flights";
    protected static final String HOTEL_RELATION = BASE_RELATION + "hotels";
    
    private static LinkManager manager;
    
    public static synchronized LinkManager getInstance() {
        if(manager == null){
            manager = new LinkManager();
        }
        
        return manager;
    }
    
    public void addLinks(Itinerary itinerary, Representation representation) {
        if (itinerary.getState()==Itinerary.ItinerayState.PLANNING) {
            addPlanningStateLinks(itinerary.getID(), representation);
        }else if (itinerary.getState()==Itinerary.ItinerayState.BOOKED) {
            addBookedStateLinks(itinerary.getID(), representation);
        }
    }
        
    private void addPlanningStateLinks(int itineraryID, Representation representation) {
        addSelfLink(itineraryID, representation);
        addRemoveLink(itineraryID, representation);
        addFlightLink(itineraryID, representation);
        addHotelLink(itineraryID, representation);
        addBookingLink(itineraryID, representation);
    }
    
    private void addBookedStateLinks(int itineraryID, Representation representation) {
        addSelfLink(itineraryID, representation);
        addCancelLink(itineraryID, representation);
    }
    
    private void addSelfLink(int itineraryID, Representation representation) {
        Link link = new Link(MEDIATYPE, BASE_URL+itineraryID+"/", SELF_RELATION);
        representation.addLink(link);
    }
    
    private void addRemoveLink(int itineraryID, Representation representation) {
        Link link = new Link(MEDIATYPE, BASE_URL+itineraryID+"/", REMOVE_RELATION);
        representation.addLink(link);
    }    
     
    private void addFlightLink(int itineraryID, Representation representation) {
        Link link = new Link(MEDIATYPE, BASE_URL+itineraryID+"/flight/", FLIGHT_RELATION);
        representation.addLink(link);
    }
    
    private void addHotelLink(int itineraryID, Representation representation) {
        Link link = new Link(MEDIATYPE, BASE_URL+itineraryID+"/hotel/", HOTEL_RELATION);
        representation.addLink(link);
    }
        
    private void addBookingLink(int itineraryID, Representation representation) {
        Link link = new Link(MEDIATYPE, BASE_URL+itineraryID+"/booking/", BOOKING_RELATION);
        representation.addLink(link);
    }
    
    private void addCancelLink(int itineraryID, Representation representation) {
        Link link = new Link(MEDIATYPE, BASE_URL+itineraryID+"/booking/", CANCEL_RELATION);
        representation.addLink(link);
    }       
         
}
