/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.dtu.niceview;

import dk.dtu.imm.fastmoney.types.CreditCardInfoType;
import javax.jws.WebService;
import javax.xml.datatype.XMLGregorianCalendar;
import ws.dtu.niceview.model.HotelDatabase;

/**
 *
 * @author Martin
 */
@WebService(serviceName = "NiceView", portName = "NiceViewPort", endpointInterface = "ws.dtu.niceview.NiceViewPortType", targetNamespace = "http://niceview.dtu.ws/", wsdlLocation = "WEB-INF/wsdl/NiceView/NiceView.wsdl")
public class NiceView {

    public NiceView() {
        HotelDatabase.loadDatabase();
    }

    public HotelList getHotels(String city, XMLGregorianCalendar arrival, XMLGregorianCalendar departure) {
        return HotelDatabase.getHotels(city, arrival, departure);
    }

    public boolean bookHotel(String bookingNo, CreditCardInfoType ccInformation) {
        HotelInformation hotel = HotelDatabase.getHotel(bookingNo);
        // Book hotel
        return true;
    }

    public boolean cancelHotel(String bookingNo) {
        HotelInformation hotel = HotelDatabase.getHotel(bookingNo);
        // Cancel hotel
        return true;
    }
    
}
