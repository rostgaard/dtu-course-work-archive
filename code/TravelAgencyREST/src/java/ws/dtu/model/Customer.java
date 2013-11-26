/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.dtu.model;

import dk.dtu.imm.fastmoney.types.CreditCardInfoType;

/**
 *
 * @author peter
 */
public class Customer {
    
    private String name;
    private CreditCardInfoType creditcard;
    private int customerID;

    public Customer(int customerID, String name) {
        this.customerID=customerID;
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public CreditCardInfoType getCreditcard() {
        return creditcard;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public void setCreditcard(CreditCardInfoType cr1) {
        this.creditcard = creditcard;
    }
    
}
