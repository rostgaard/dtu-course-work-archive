/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import javax.xml.datatype.XMLGregorianCalendar;
import org.junit.Test;
import org.netbeans.j2ee.wsdl.webapplication1.java.newwsdl.MyComplexType;
import ws.lameduck.FlightInformations;

/**
 *
 * @author Mikkel
 */
public class NewEmptyJUnitTest {
    
    public NewEmptyJUnitTest() {
    }
    
    @Test
    public void test(){
        XMLGregorianCalendar date1 = new XMLGregorianCalendarImpl();
        date1.setMonth(12);
        date1.setDay(20);
        date1.setYear(2012);

        XMLGregorianCalendar date2 = new XMLGregorianCalendarImpl();
        date2.setMonth(12);
        date2.setDay(21);
        date2.setYear(2012);
        
        MyComplexType resp = myOperation();
        int i = 0;
        
        
    }

    private static MyComplexType myOperation() {
        org.netbeans.j2ee.wsdl.webapplication1.java.newwsdl.DateTestService service = new org.netbeans.j2ee.wsdl.webapplication1.java.newwsdl.DateTestService();
        org.netbeans.j2ee.wsdl.webapplication1.java.newwsdl.DateTestPortType port = service.getDateTestPortTypeBindingPort();
        return port.myOperation();
    }

    private static FlightInformations getFlight(java.lang.String startAirport, java.lang.String destinationAirport, javax.xml.datatype.XMLGregorianCalendar date) {
        ws.lameduck.LameDuckWSDLService service = new ws.lameduck.LameDuckWSDLService();
        ws.lameduck.LameDuckWSDLPortType port = service.getLameDuckWSDLPort();
        return port.getFlight(startAirport, destinationAirport, date);
    }

 

    
}
