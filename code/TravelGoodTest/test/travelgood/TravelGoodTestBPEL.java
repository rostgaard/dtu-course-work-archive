/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package travelgood;

import org.junit.Test;
import static org.junit.Assert.*;
import servicewrappers.BPELWrapper;

/**
 *
 * @author Mikkel
 */
public class TravelGoodTestBPEL extends TravelGoodTest {
    
    public TravelGoodTestBPEL() {
        super();
        client = new BPELWrapper();
    }
}
