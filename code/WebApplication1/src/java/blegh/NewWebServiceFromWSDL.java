/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blegh;

import javax.jws.WebService;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.netbeans.j2ee.wsdl.webapplication1.java.newwsdl.NewComplexType;

/**
 *
 * @author Mikkel
 */
@WebService(serviceName = "newWSDLService", portName = "newWSDLPort", endpointInterface = "org.netbeans.j2ee.wsdl.webapplication1.java.newwsdl.NewWSDLPortType", targetNamespace = "http://j2ee.netbeans.org/wsdl/WebApplication1/java/newWSDL", wsdlLocation = "WEB-INF/wsdl/NewWebServiceFromWSDL/newWSDL.wsdl")
public class NewWebServiceFromWSDL {

     public org.netbeans.j2ee.wsdl.webapplication1.java.newwsdl.NewComplexType newWSDLOperation() throws DatatypeConfigurationException {
        NewComplexType type = new NewComplexType();
        DatatypeFactory df = DatatypeFactory.newInstance();
            XMLGregorianCalendar date3 = df.newXMLGregorianCalendar("2099-11-11");
            type.setNewElement(date3);
            return type;
    }
    
}
