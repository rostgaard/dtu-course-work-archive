/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.dtu.resources.utils;

import java.util.Date;
import ws.dtu.resources.model.HotelDatabase;
import ws.dtu.resources.model.HotelInformation;

/**
 *
 * @author krc
 */
public final class DummyDatabasePolulator {
    
    
    public void createHotelDB() {
        HotelDatabase.insert(new HotelInformation("Kabul", "Hotel Noisy Neighbours"));
        HotelDatabase.insert(new HotelInformation("Kazakhstan", "The Plaza with cockroaches"));
        HotelDatabase.insert(new HotelInformation("Irak", "At Saddams"));
        HotelDatabase.insert(new HotelInformation("Libia", "At Gaddafi's"));
    }
}
