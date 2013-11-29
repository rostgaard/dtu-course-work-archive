/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.junit.Test;
import static org.junit.Assert.*;
import ws.testdates.TestComplexType;

/**
 *
 * @author Martin
 */
public class TestDatesTest {
    
    @Test
    public void testDates() throws DatatypeConfigurationException {
        TestComplexType type = new TestComplexType();
        type.setStringElement("This is a string");
        
        DatatypeFactory df = DatatypeFactory.newInstance();
        
        XMLGregorianCalendar date1 = df.newXMLGregorianCalendar("2013-10-11");
        XMLGregorianCalendar date2 = df.newXMLGregorianCalendar("2013-10-12T10:00:00");
        
        type.setDateElement(date1);
        type.setDatetimeElement(date2);
        
        assertNotNull(type.getDateElement());
        assertNotNull(type.getDatetimeElement());
        
        TestComplexType type2 = testDatesOperation(type);
        
        assertNotNull(type2.getDateElement());
        assertNotNull(type2.getDatetimeElement());
        
        assertEquals(type.getStringElement(), type2.getStringElement());
        assertEquals(type.getDateElement(), type2.getDateElement());
        assertEquals(type.getDatetimeElement(), type2.getDatetimeElement());
    }
    
    @Test
    public void testDatetimeNull() throws DatatypeConfigurationException {
        TestComplexType type = new TestComplexType();
        type.setStringElement("This is a string");
        
        DatatypeFactory df = DatatypeFactory.newInstance();
        
        XMLGregorianCalendar date1 = df.newXMLGregorianCalendar("2013-10-11");
        XMLGregorianCalendar date2 = df.newXMLGregorianCalendar("2013-10-12");
        
        type.setDateElement(date1);
        type.setDatetimeElement(date2);
        
        assertNotNull(type.getDateElement());
        assertNotNull(type.getDatetimeElement());
        
        TestComplexType type2 = testDatesOperation(type);
        
        assertNotNull(type2.getDateElement());
        assertNull(type2.getDatetimeElement());
        
        assertEquals(type.getStringElement(), type2.getStringElement());
        assertEquals(type.getDateElement(), type2.getDateElement());
    }
            

    private static TestComplexType testDatesOperation(TestComplexType dateIn) {
        ws.testdates.TestDatesService service = new ws.testdates.TestDatesService();
        ws.testdates.TestDatesPortType port = service.getTestDatesPort();
        return port.testDatesOperation(dateIn);
    }
}
