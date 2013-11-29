/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.dtu.manager;
import java.util.HashMap;
import ws.dtu.model.Itinerary;
import ws.dtu.model.exceptions;

/**
 *
 * @author krc
 */
public final class ItineraryDatabase {
    

    private static HashMap<Integer,HashMap<Integer, Itinerary>> data = new HashMap<Integer, HashMap<Integer, Itinerary>>();
    
    private static ItineraryDatabase db;
    
    public static synchronized ItineraryDatabase getInstance() {
        if(db == null){
            db = new ItineraryDatabase();
        }
        
        return db;
    }
    
    public Itinerary get(int customerID, int itinerary_id) {
        if(!data.containsKey(customerID)) {
            throw new exceptions.NoSuchCustomerException();
        }
        if(!data.get(customerID).containsKey(itinerary_id)) {
            throw new exceptions.NoSuchItineraryException();
        }
        
        return data.get(customerID).get(itinerary_id);
    }
    
    public void insert (Itinerary itinerary, int customer_id) {
        HashMap<Integer, Itinerary> map = null;
        if (data.containsKey(customer_id)) {
            map = data.get(customer_id);

        }else {
            map = new HashMap<Integer, Itinerary>();
        }
        
        map.put(itinerary.getID(), itinerary);
        data.put(new Integer(customer_id), map);
    }
    
    public void delete (int customer_id, int itinerary_id) {
        if(!data.containsKey(customer_id)) {
            throw new exceptions.NoSuchCustomerException();
        }
        if(!data.get(customer_id).containsKey(itinerary_id)) {
            throw new exceptions.NoSuchItineraryException();
        }
        
        data.get(customer_id).remove(itinerary_id);
        data.remove(new Integer(itinerary_id));
    }
    
    public void reset() {
        data.clear();
    }
}