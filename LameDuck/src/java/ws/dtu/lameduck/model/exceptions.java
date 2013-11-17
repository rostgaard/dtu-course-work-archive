/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.dtu.lameduck.model;

/**
 *
 * @author krc
 */
public final class exceptions {
    public static class Seat_Unavailable extends Throwable{

        public Seat_Unavailable() {
        }
    }
    
    public static class NoSuchFlightIdentifier extends Throwable{

        public NoSuchFlightIdentifier() {
        }
    }
    
}
