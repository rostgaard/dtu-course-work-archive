/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.dtu.resources.model;
import java.util.HashMap;
import ws.dtu.resources.model.exceptions.NoSuchIdentifier;

/**
 *
 * @author krc
 */
public final class ItineraryDatabase {
    
    static HashMap<Integer,Itinerary> db = new HashMap<Integer, Itinerary>();
    
    public static Itinerary get(int itinerary_id) throws NoSuchIdentifier {
        if(!db.containsKey(itinerary_id)) {
            throw new NoSuchIdentifier();
        }
        
        return db.get(itinerary_id);
    }
    
    public static void insert (Itinerary itinerary) {
        db.put(new Integer (itinerary.getID()), itinerary);
    }
    
    public static void delete (int itinerary_id) throws NoSuchIdentifier {
        if(!db.containsKey(itinerary_id)) {
            throw new NoSuchIdentifier();
        }
        db.remove(new Integer(itinerary_id));
    }

}
