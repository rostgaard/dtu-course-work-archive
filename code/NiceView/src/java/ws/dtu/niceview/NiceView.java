package ws.dtu.niceview;

import dk.dtu.imm.fastmoney.BankService;
import dk.dtu.imm.fastmoney.CreditCardFaultMessage;
import dk.dtu.imm.fastmoney.types.AccountType;
import dk.dtu.imm.fastmoney.types.CreditCardInfoType;
import javax.jws.WebService;
import javax.xml.datatype.XMLGregorianCalendar;
import ws.dtu.niceview.model.HotelDatabase;
import ws.dtu.niceview.types.HotelInformation;
import ws.dtu.niceview.types.HotelList;

@WebService(serviceName = "NiceViewService", portName = "NiceViewPort", endpointInterface = "ws.dtu.niceview.NiceViewPortType", targetNamespace = "http://niceview.dtu.ws/", wsdlLocation = "WEB-INF/wsdl/NiceView/NiceView.wsdl")
public class NiceView {

    private static final int GROUP = 3;
    private AccountType account;
    private BankService service = new BankService();
    private HotelDatabase db;

    public NiceView() {
        db = HotelDatabase.getInstance();

        account = new AccountType();
        account.setName("NiceView");
        account.setNumber("50308815");

    }

    public HotelList getHotels(String city, XMLGregorianCalendar arrival, XMLGregorianCalendar departure) {
        return db.getHotels(city, arrival, departure);
    }

    public boolean bookHotel(String bookingNo, CreditCardInfoType ccInformation) throws CreditCardFaultMessage, BookHotelFault {
        HotelInformation hotel = db.bookHotel(bookingNo);
        if (hotel.isCcRequired()) {
            try {
                return validateCreditCard(GROUP, ccInformation, (int) hotel.getPrice());
            } catch (CreditCardFaultMessage e) {
                throw new BookHotelFault("Credit card validation failed", "Credit card validation failed", e);
            }
        }

        return true;
    }

    public boolean cancelHotel(String bookingNo) throws CancelHotelFault {
        db.cancelHotel(bookingNo);
        return true;
    }

    private boolean validateCreditCard(int group, CreditCardInfoType creditCardInfo, int amount) throws CreditCardFaultMessage {
        dk.dtu.imm.fastmoney.BankPortType port = service.getBankPort();
        return port.validateCreditCard(group, creditCardInfo, amount);
    }
}
