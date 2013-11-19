/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lameduck;

import javax.jws.WebService;

/**
 *
 * @author Mikkel
 */
@WebService(serviceName = "LameDuckService", portName = "LameDuckPortTypeBindingPort", endpointInterface = "ws.dtu.lameduck.LameDuckPortType", targetNamespace = "http://lameduck.dtu.ws", wsdlLocation = "WEB-INF/wsdl/LameDuck/LameDuckService.wsdl")
public class LameDuck {

    public ws.dtu.lameduck.FlightList getFlights(java.lang.String origin, java.lang.String destination, javax.xml.datatype.XMLGregorianCalendar date) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public boolean bookFlight(java.lang.String bookingNumber, dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCardInfo) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }
    
}
