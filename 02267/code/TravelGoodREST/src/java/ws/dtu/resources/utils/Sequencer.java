/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.dtu.resources.utils;


public final class Sequencer {

    protected static int sequence = 0;
    
    public static int getNext() {
        return ++sequence;
    }
}
