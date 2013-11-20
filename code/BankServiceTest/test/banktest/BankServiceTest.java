/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package banktest;

import dk.dtu.imm.fastmoney.CreditCardFaultMessage;
import dk.dtu.imm.fastmoney.types.AccountType;
import dk.dtu.imm.fastmoney.types.CreditCardInfoType;
import dk.dtu.imm.fastmoney.types.ExpirationDateType;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Mikkel
 */
public class BankServiceTest {
    
    private int group = 3;
    private CreditCardInfoType card1;
    private AccountType account;
    
    public BankServiceTest() {
        card1 = new CreditCardInfoType();
        card1.setName("Bech Camilla");
        card1.setNumber("50408822");
        ExpirationDateType expDate = new ExpirationDateType();
        expDate.setMonth(7);
        expDate.setYear(9);
        card1.setExpirationDate(expDate);
        
        account = new AccountType();
        account.setName("LameDuck");
        account.setNumber("50208812");
    
    }
    
    
    @Test
    public void TestChargeSucces() throws CreditCardFaultMessage{
        
        boolean result = chargeCreditCard(group,card1,50,account);
        
        assertTrue(result);
        
    }
    
    @Test
    public void TestRefundSucces() throws CreditCardFaultMessage{
        
         boolean result = refundCreditCard(group,card1,50,account);
         
         assertTrue(result);
    }
    
    @Test(expected = CreditCardFaultMessage.class)
    public void TestChargeTooHigh() throws CreditCardFaultMessage {
        
        boolean result = chargeCreditCard(group,card1,5000,account);
        
        
        
    }

    private static boolean chargeCreditCard(int group, dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCardInfo, int amount, dk.dtu.imm.fastmoney.types.AccountType account) throws CreditCardFaultMessage {
        dk.dtu.imm.fastmoney.BankService service = new dk.dtu.imm.fastmoney.BankService();
        dk.dtu.imm.fastmoney.BankPortType port = service.getBankPort();
        return port.chargeCreditCard(group, creditCardInfo, amount, account);
    }

    private static boolean refundCreditCard(int group, dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCardInfo, int amount, dk.dtu.imm.fastmoney.types.AccountType account) throws CreditCardFaultMessage {
        dk.dtu.imm.fastmoney.BankService service = new dk.dtu.imm.fastmoney.BankService();
        dk.dtu.imm.fastmoney.BankPortType port = service.getBankPort();
        return port.refundCreditCard(group, creditCardInfo, amount, account);
    }

    private static boolean validateCreditCard(int group, dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCardInfo, int amount) throws CreditCardFaultMessage {
        dk.dtu.imm.fastmoney.BankService service = new dk.dtu.imm.fastmoney.BankService();
        dk.dtu.imm.fastmoney.BankPortType port = service.getBankPort();
        return port.validateCreditCard(group, creditCardInfo, amount);
    }
}
