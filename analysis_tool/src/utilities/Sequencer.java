/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

/**
 *
 * @author krc
 */
public class Sequencer {
    
    protected static int Sequence = 0;
    
    public static int nextInt() {
        return ++Sequence;
    }
    
}
