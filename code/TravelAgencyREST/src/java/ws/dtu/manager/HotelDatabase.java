/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.dtu.manager;

import java.util.HashMap;
import ws.dtu.niceview.types.HotelInformation;

/**
 *
 * @author krc
 */
public final class HotelDatabase {
    
    static HashMap<String,HotelInformation> db = new HashMap<String, HotelInformation>();
    
    public static void insert (HotelInformation hotel) {
        db.put(hotel.getName(), hotel);
    }

}
