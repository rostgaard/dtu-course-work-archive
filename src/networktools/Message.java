/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package networktools;

import java.io.Serializable;

/**
 *
 * @author krc
 */
public class Message implements Serializable {

    private int destinationProcess;
    private int sourceProcess;
    private Object payload;
}
