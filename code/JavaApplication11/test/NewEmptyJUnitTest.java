/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.xml.datatype.XMLGregorianCalendar;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Mikkel
 */
public class NewEmptyJUnitTest {
    
    public NewEmptyJUnitTest() {
    }
    
    @Test
    public void test(){
        newWSDLOperation();
    }

    private static XMLGregorianCalendar newWSDLOperation() {
        org.netbeans.j2ee.wsdl.webapplication1.java.newwsdl.NewWSDLService service = new org.netbeans.j2ee.wsdl.webapplication1.java.newwsdl.NewWSDLService();
        org.netbeans.j2ee.wsdl.webapplication1.java.newwsdl.NewWSDLPortType port = service.getNewWSDLPort();
        return port.newWSDLOperation();
    }
}
