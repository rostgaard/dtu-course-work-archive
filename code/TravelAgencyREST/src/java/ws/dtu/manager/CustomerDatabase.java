/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.dtu.manager;

import dk.dtu.imm.fastmoney.types.CreditCardInfoType;
import dk.dtu.imm.fastmoney.types.ExpirationDateType;
import java.util.HashMap;
import ws.dtu.model.Customer;
import ws.dtu.model.exceptions;
import ws.dtu.model.exceptions.NoSuchCustomerIdentifier;

/**
 *
 * @author peter
 */
public class CustomerDatabase {
    
    HashMap<Integer, Customer> customers = new HashMap<Integer, Customer>();
    
    private static CustomerDatabase db;
    
    public static synchronized CustomerDatabase getInstance() {
        if(db == null){
            db = new CustomerDatabase();
            db.loadDatabase();
        }
        
        return db;
    }

    private void loadDatabase() {
        add(1, "Anne Strandberg",5,9,"50408816");
        add(2, "Klinkby Poul",3,10,"50408817");
        add(3, "Donovan Jasper",6,9,"50408818");
        add(4, "Dirach Anne-Louise",1,10,"50408819");
        add(5, "Brorson Bodil",2,10,"50408820");
        add(6, "Bech Camilla",7,9,"50408822");
        add(7, "Tobiasen Inge",9,10,"50408823");
        add(7, "Tick Joachim",2,11,"50408824");
        add(7, "Thor-Jensen Claus",5,9,"50408825");
    }
    
    private void add(int customerID, String name, int expirationMonth, int expirationYear, String cardNumber) {
        Customer c = new Customer(customerID, name);
        CreditCardInfoType cr = new CreditCardInfoType();
        ExpirationDateType edt = new ExpirationDateType();
        edt.setMonth(expirationMonth);
        edt.setYear(expirationYear);
        cr.setExpirationDate(edt);
        cr.setNumber(cardNumber);
        c.setCreditcard(cr);
        customers.put(c.getCustomerID(), c);
    }
    
    public Customer get(int customerID) throws NoSuchCustomerIdentifier {
        if(!customers.containsKey(customerID)) {
            throw new exceptions.NoSuchCustomerIdentifier();
        }
        
        return customers.get(customerID);
    }
}
