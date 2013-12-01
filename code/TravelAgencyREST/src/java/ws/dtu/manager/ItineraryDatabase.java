/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.dtu.manager;
import java.util.HashMap;
import ws.dtu.model.Customer;
import ws.dtu.model.Itinerary;
import ws.dtu.model.exceptions;

/**
 *
 * @author krc
 */
public final class ItineraryDatabase {
    

    private static HashMap<Customer,HashMap<Integer, Itinerary>> data = new HashMap<Customer, HashMap<Integer, Itinerary>>();
    
    private static ItineraryDatabase db;
    private CustomerDatabase customers = CustomerDatabase.getInstance();
    
    public static synchronized ItineraryDatabase getInstance() {
        if(db == null){
            db = new ItineraryDatabase();
        }
        
        return db;
    }
    
    public Itinerary get(int customerID, int itinerary_id) {
        Customer customer = customers.get(customerID);
        if(!data.containsKey(customer)) {
            throw new exceptions.NoSuchCustomerException();
        }
        if(!data.get(customer).containsKey(itinerary_id)) {
            throw new exceptions.NoSuchItineraryException();
        }
        
        return data.get(customer).get(itinerary_id);
    }
    
    public void insert (Itinerary itinerary, int customer_id) {
        Customer customer = customers.get(customer_id);
        HashMap<Integer, Itinerary> map = null;
        if (data.containsKey(customer)) {
            map = data.get(customer);

        }else {
            map = new HashMap<Integer, Itinerary>();
        }
        
        map.put(itinerary.getID(), itinerary);
        data.put(customer, map);
    }
    
    public void delete (int customer_id, int itinerary_id) {
        Customer customer = customers.get(customer_id);
        if(!data.containsKey(customer)) {
            throw new exceptions.NoSuchCustomerException();
        }
        if(!data.get(customer).containsKey(itinerary_id)) {
            throw new exceptions.NoSuchItineraryException();
        }
        
        data.get(customer).remove(itinerary_id);
        data.remove(customer);
    }
    
    public void reset() {
        data.clear();
    }
}