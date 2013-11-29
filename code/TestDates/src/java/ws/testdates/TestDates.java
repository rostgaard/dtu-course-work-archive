/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.testdates;

import javax.jws.WebService;

/**
 *
 * @author Martin
 */
@WebService(serviceName = "TestDatesService", portName = "TestDatesPort", endpointInterface = "ws.testdates.TestDatesPortType", targetNamespace = "http://testdates.ws/", wsdlLocation = "WEB-INF/wsdl/TestDates/TestDates.wsdl")
public class TestDates {

    public ws.testdates.TestComplexType testDatesOperation(ws.testdates.TestComplexType dateIn) {
        return dateIn;
    }
    
}
