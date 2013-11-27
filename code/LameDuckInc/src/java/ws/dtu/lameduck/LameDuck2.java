/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.dtu.lameduck;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import javax.jws.WebService;
import javax.xml.datatype.XMLGregorianCalendar;
import ws.dtu.lameduck.types.*;

/**
 *
 * @author Mikkel
 */
@WebService(serviceName = "LameDuckService", portName = "LameDuckPortTypeBindingPort", endpointInterface = "ws.dtu.lameduck.LameDuckPortType", targetNamespace = "http://lameduck.dtu.ws", wsdlLocation = "WEB-INF/wsdl/LameDuck2/LameDuck2.wsdl")
public class LameDuck2 {

    public ws.dtu.lameduck.types.FlightList getFlights(java.lang.String origin, java.lang.String destination, javax.xml.datatype.XMLGregorianCalendar liftOff) {
        Flight fl = new Flight();
        XMLGregorianCalendar date = new XMLGregorianCalendarImpl();
        date.setDay(1);
        date.setMonth(1);
        date.setYear(2011);
        fl.setLiftOff(date);
        FlightInformation fi = new FlightInformation();
        fi.setFlight(fl);
        fl.setArrival(date);
        FlightList flights = new FlightList();
        flights.getFlights().add(fi);
        return flights;
    }

    public boolean bookFlight(java.lang.String bookingNumber, dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCardInfo) throws BookFlightFault {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }
    
}
