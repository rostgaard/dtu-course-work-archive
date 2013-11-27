/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package qweqwe;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import javax.jws.WebService;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.netbeans.j2ee.wsdl.webapplication1.java.newwsdl.MyComplexType;

/**
 *
 * @author Mikkel
 */
@WebService(serviceName = "DateTestService", portName = "DateTestPortTypeBindingPort", endpointInterface = "org.netbeans.j2ee.wsdl.webapplication1.java.newwsdl.DateTestPortType", targetNamespace = "http://j2ee.netbeans.org/wsdl/WebApplication1/java/newWSDL", wsdlLocation = "WEB-INF/wsdl/NewWebServiceFromWSDL/DateTestWrapped.wsdl")
public class NewWebServiceFromWSDL {

    public org.netbeans.j2ee.wsdl.webapplication1.java.newwsdl.MyComplexType myOperation() throws DatatypeConfigurationException {
        DatatypeFactory df = DatatypeFactory.newInstance();
        
        XMLGregorianCalendar date = df.newXMLGregorianCalendar("2013-11-17");
       // XMLGregorianCalendar date = new XMLGregorianCalendarImpl();
       // date.setDay(1);
       // date.setYear(2013);
       // date.setMonth(1);
        String s = "Tester";
        
        
        MyComplexType comp = new MyComplexType();
        comp.setMyDate(date);
        comp.setMyString(s);
        
        return comp;

    }
    
}
