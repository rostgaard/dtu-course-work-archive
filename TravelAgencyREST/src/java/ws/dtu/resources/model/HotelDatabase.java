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
public final class HotelDatabase {
    
    static HashMap<String,HotelInformation> db = new HashMap<String, HotelInformation>();
    
    public static void insert (HotelInformation hotel) {
        db.put(hotel.getName(), hotel);
    }

}
