/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lameducktest;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import dk.dtu.imm.fastmoney.types.CreditCardInfoType;
import javax.xml.datatype.XMLGregorianCalendar;
import org.junit.Test;
import static org.junit.Assert.*;
import ws.dtu.lameduck.types.FlightList;

/**
 *
 * @author Mikkel
 */
public class LameDuckIncTest {
    
    public LameDuckIncTest() {
    }
    
    @Test
    public void test(){
        XMLGregorianCalendar date = new XMLGregorianCalendarImpl();
        date.setDay(1);
        date.setMonth(1);
        date.setYear(2011);
        ws.dtu.lameduck.types.FlightList fl = getFlights("","",date);
        fl.getFlights().get(0).getFlight().getLiftOff().getDay();
        fl.getFlights().get(0).getFlight().getArrival().getDay();
    }

    private static FlightList getFlights(java.lang.String origin, java.lang.String destination, javax.xml.datatype.XMLGregorianCalendar liftOff) {
        ws.dtu.lameduck.LameDuckService service = new ws.dtu.lameduck.LameDuckService();
        ws.dtu.lameduck.LameDuckPortType port = service.getLameDuckPortTypeBindingPort();
        return port.getFlights(origin, destination, liftOff);
    }

   




}
