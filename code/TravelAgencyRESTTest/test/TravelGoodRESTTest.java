/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.Before;
import ws.dtu.RestService;

/**
 * 
 * @author peter
 */

public class TravelGoodRESTTest {
    
    protected final static  String customerID = "1";
    
    protected static final String BASE_RELATION = "http://itinerary.ws/relations/";

    protected static final String SELF_RELATION = BASE_RELATION + "self";
    protected static final String REMOVE_RELATION = BASE_RELATION + "remove";    
    protected static final String CANCEL_RELATION = BASE_RELATION + "cancel";
    protected static final String BOOKING_RELATION = BASE_RELATION + "booking";
    protected static final String FLIGHT_RELATION = BASE_RELATION + "flights";
    protected static final String HOTEL_RELATION = BASE_RELATION + "hotels";
    
    protected String date1, date2;

    
    public TravelGoodRESTTest() {
    }
    
    
    @Before
    public void beforeStart() {        
        date1 = "2013-11-24";
        date2= "2013-11-25";
    }

    
    @After
    public void tearDown() {
        RestService.reset();
    }
    
}