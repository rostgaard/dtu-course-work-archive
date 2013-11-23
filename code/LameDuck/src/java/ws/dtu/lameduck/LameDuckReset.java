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
@WebService(serviceName = "LameDuckResetService", portName = "LameDuckResetPort", endpointInterface = "reset.lameduck.dtu.ws.LameDuckResetPortType", targetNamespace = "http://ws.dtu.lameduck.reset", wsdlLocation = "WEB-INF/wsdl/LameDuckReset/LameDuckReset.wsdl")
public class LameDuckReset {

    public void lameDuckResetOperation() {
        FlightDatabase.getInstance().reset();
    }
    
}
