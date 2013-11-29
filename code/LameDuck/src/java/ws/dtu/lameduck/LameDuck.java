package ws.dtu.lameduck;

import dk.dtu.imm.fastmoney.BankPortType;
import dk.dtu.imm.fastmoney.BankService;
import dk.dtu.imm.fastmoney.CreditCardFaultMessage;
import dk.dtu.imm.fastmoney.types.AccountType;
import dk.dtu.imm.fastmoney.types.CreditCardInfoType;
import javax.jws.WebService;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;
import ws.dtu.lameduck.model.FlightDatabase;
import ws.dtu.lameduck.types.FlightInformation;
import ws.dtu.lameduck.types.FlightList;


@WebService(serviceName = "LameDuckService", portName = "LameDuckPort", endpointInterface = "ws.dtu.lameduck.LameDuckPortType", targetNamespace = "http://lameduck.dtu.ws/", wsdlLocation = "WEB-INF/wsdl/LameDuck/LameDuck.wsdl")
public class LameDuck {
private AccountType account;
    private static final int GROUP = 3;
    private BankService service = new BankService();
    private FlightDatabase db;
    
    public LameDuck() throws DatatypeConfigurationException {
        db = FlightDatabase.getInstance();
        account = new AccountType();
        account.setName("LameDuck");
        account.setNumber("50208812");
    }
    

    public FlightList getFlights(String origin, String destination, XMLGregorianCalendar liftOff) {
        FlightList list = db.getFlights(origin, destination, liftOff);
        
        return list;
    }

    public boolean bookFlight(String bookingNumber, CreditCardInfoType creditCardInfo) throws BookFlightFault {
        FlightInformation fi = db.bookFlight(bookingNumber);
        try {
            return chargeCreditCard(GROUP, creditCardInfo, (int)fi.getPrice(), account);
        } catch (CreditCardFaultMessage ex) {
            throw new BookFlightFault("Credit card payment failed", "Credit card payment failed", ex);
        }
    }

    public boolean cancelFlight(java.lang.String bookingNumber, int price, dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCardInfo) throws CancelFlightFault { 
         try {
            refundCreditCard(GROUP,creditCardInfo, price, account);
            db.cancelFlight(bookingNumber);
            return true;
        } catch (dk.dtu.imm.fastmoney.CreditCardFaultMessage ex) {
            throw new CancelFlightFault("Cancel flight failed due to exception from the bank: " + ex.getMessage(), "Bank failed to refund money.", ex);
        }
    }

    private boolean chargeCreditCard(int group, dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCardInfo, int amount, dk.dtu.imm.fastmoney.types.AccountType account) throws CreditCardFaultMessage {
        BankPortType port = service.getBankPort();
        return port.chargeCreditCard(group, creditCardInfo, amount, account);
    }

    private boolean refundCreditCard(int group, dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCardInfo, int amount, dk.dtu.imm.fastmoney.types.AccountType account) throws CreditCardFaultMessage {
        dk.dtu.imm.fastmoney.BankPortType port = service.getBankPort();
        return port.refundCreditCard(group, creditCardInfo, amount, account);
    }
    
}