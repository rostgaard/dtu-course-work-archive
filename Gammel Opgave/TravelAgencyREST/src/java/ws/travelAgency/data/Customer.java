package ws.travelAgency.data;

import dk.dtu.imm.fastmoney.types.CreditCardInfoType;
import dk.dtu.imm.fastmoney.types.ExpirationDateType;
import java.util.HashMap;

/**
 *
 * @author Andreas
 */
public class Customer {
    static int next_id = 0;
    int id;
    String name;
    HashMap<String,Itinerary> itineraries = new HashMap<String,Itinerary>();
    CreditCardInfoType creditCard = new CreditCardInfoType();

    
    public static void reset() {
        next_id = 0; 
    }

    public Customer(String name, int CCNumber, int CCExpirationMonth, int CCExpirationYear) {
        id = next_id++;
        this.name = name;
        creditCard.setNumber("" + CCNumber);

        ExpirationDateType expirationDate = new ExpirationDateType();
        expirationDate.setMonth(CCExpirationMonth);
        expirationDate.setYear(CCExpirationYear);

        creditCard.setExpirationDate(expirationDate);
        creditCard.setName(name);
    }

    public int getId() {
        return id; 
    }

    public void addItinerary(Itinerary intinerary) {
        itineraries.put(intinerary.getID(), intinerary);
    }

    public Itinerary getItinerary(String itinerary_ID) {
        return itineraries.get(itinerary_ID);
    }

    public void removeItinerary(String itinerary_ID) {
        itineraries.remove(itinerary_ID);
    }

    public void setCreditCardNumber(int creditCardNumber) {
        creditCard.setNumber("" + creditCardNumber);
    }

    public int getCreditCardNumber() {
        return Integer.parseInt(creditCard.getNumber()); 
    }

    public void setCreditCardExpirationMonth(int month) {
       creditCard.getExpirationDate().setMonth(month);
    }

    public int getCreditCardExpirationMonth() {
        return creditCard.getExpirationDate().getMonth();
    }

    public void setCreditCardExpirationYear(int year) {
        creditCard.getExpirationDate().setYear(year);
    }

    public int getCreditCardExpirationYear() {
        return creditCard.getExpirationDate().getYear();
    }

    public CreditCardInfoType getCreditCard() {
        return creditCard; 
    }

    public String getName() {
        return name; 
    }
}
