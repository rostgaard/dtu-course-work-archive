/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.dtu.model;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import ws.dtu.entities.ErrorBean;

/**
 *
 * @author krc
 */
public final class exceptions {

        
    public static class NotFoundException extends WebApplicationException{
        
        public NotFoundException() {
            super(Response.status(Response.Status.NOT_FOUND).entity( new ErrorBean("",Response.Status.NOT_FOUND.getStatusCode())).build());
        }
        
        public NotFoundException(String message) {
            super(Response.status(Response.Status.NOT_FOUND).entity( new ErrorBean(message,Response.Status.NOT_FOUND.getStatusCode())).build());
        }

    } 
    
    public static class BookingException extends WebApplicationException{
        
        public BookingException() {
            super(Response.status(Response.Status.NOT_ACCEPTABLE).entity( new ErrorBean("Failed to book",Response.Status.NOT_FOUND.getStatusCode())).build());
        }

    } 
    
    public static class CancelException extends WebApplicationException{
        
        public CancelException() {
            super(Response.status(Response.Status.NOT_ACCEPTABLE).entity( new ErrorBean("Failed to cancel",Response.Status.NOT_FOUND.getStatusCode())).build());
        }

    } 
    
    public static class NoSuchCustomerException extends NotFoundException{
        public NoSuchCustomerException() {
            super("Customer not found");
        }  
        
    }
    
    public static class NoSuchItineraryException extends NotFoundException{
        public NoSuchItineraryException() {
            super("Itinerary not found");
        }  
        
    }
}
