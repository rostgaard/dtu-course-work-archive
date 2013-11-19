/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.dtu.lameduck;

import javax.jws.WebService;
import ws.dtu.lameduck.model.FlightDatabase;

/**
 *
 * @author Mikkel
 */
@WebService(serviceName = "LameDuckService", portName = "LameDuckPortTypeBindingPort", endpointInterface = "ws.dtu.lameduck.LameDuckPortType", targetNamespace = "http://lameduck.dtu.ws", wsdlLocation = "WEB-INF/wsdl/LameDuck/LameDuckService.wsdl")
public class LameDuck {

    public LameDuck(){
        FlightDatabase.loadDatabase();
    }
    public ws.dtu.lameduck.FlightList getFlights(java.lang.String origin, java.lang.String destination, javax.xml.datatype.XMLGregorianCalendar date) {
        return FlightDatabase.getFlights(origin, destination, date);
                
    }

    public boolean bookFlight(java.lang.String bookingNumber, dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCardInfo) {
        return FlightDatabase.bookFlight(bookingNumber, creditCardInfo);
    }

    public boolean cancelFlight(java.lang.String bookingNumber, double price, dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCardInfo) throws CancelFlightFault {
        return FlightDatabase.cancelFlight(bookingNumber, price, creditCardInfo);
    }
    
}
