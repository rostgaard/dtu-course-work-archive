package ws.dtu.niceview;

import javax.jws.WebService;
import ws.dtu.niceview.model.HotelDatabase;

@WebService(serviceName = "NiceViewResetService", portName = "NiceViewResetPort", endpointInterface = "reset.niceview.dtu.ws.NiceViewResetPortType", targetNamespace = "http://ws.dtu.NiceView.reset", wsdlLocation = "WEB-INF/wsdl/NiceViewReset/NiceViewReset.wsdl")
public class NiceViewReset {

    public void niceViewResetOperation() {
        HotelDatabase.getInstance().reset();
    }
    
}
