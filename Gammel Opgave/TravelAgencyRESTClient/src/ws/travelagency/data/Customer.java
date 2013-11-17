/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.travelagency.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Andreas
 */
public class Customer {
    static int next_id = 0;
    int id;
    String name;
    HashMap<String,Itinerary> itineraries = new HashMap<String,Itinerary>();
    CreditCardInformation creditCard = new CreditCardInformation(); 

    

    public Customer(String name, int CCNumber, int CCExpirationMonth, int CCExpirationYear) {
        id = next_id++;
        this.name = name;
        creditCard.setCreditCardNumber(CCNumber);

        ExpirationDateType expirationDate = new ExpirationDateType();
        expirationDate.setMonth(CCExpirationMonth);
        expirationDate.setYear(CCExpirationYear);

        creditCard.setExpirationDate(expirationDate);
        creditCard.setHolder(name);
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

    public void setCreditCardNumber(int creditCardNumber) {
        creditCard.setCreditCardNumber(creditCardNumber);
    }

    public int getCreditCardNumber() {
        return creditCard.getCreditCardNumber();
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

    public CreditCardInformation getCreditCard() {
        return creditCard; 
    }

    public String getName() {
        return name; 
    }
}
