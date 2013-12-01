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

/**
 * 
 * @author mikkel
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
        add(5, "Brorson Bodil",7,11,"50408820");
        add(6, "Bruhn Brigitte",2,10,"50408821");
        add(7, "Bech Camilla",7,9,"50408822");
        add(8, "Tobiasen Inge",9,10,"50408823");
        add(9, "Tick Joachim",2,11,"50408824");
        add(10, "Thor-Jensen Claus",5,9,"50408825");
    }
    
    private void add(int customerID, String name, int expirationMonth, int expirationYear, String cardNumber) {
        Customer c = new Customer(customerID, name);
        CreditCardInfoType cr = new CreditCardInfoType();
        ExpirationDateType edt = new ExpirationDateType();
        edt.setMonth(expirationMonth);
        edt.setYear(expirationYear);
        cr.setExpirationDate(edt);
        cr.setNumber(cardNumber);
        cr.setName(name);
        c.setCreditcard(cr);
        customers.put(c.getCustomerID(), c);
    }
    
    public Customer get(int customerID)  {
        if(!customers.containsKey(customerID)) {
            throw new exceptions.NoSuchCustomerException();
        }
        
        return customers.get(customerID);
    }
}
