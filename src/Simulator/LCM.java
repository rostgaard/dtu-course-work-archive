/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Simulator;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author krc
 */
public class LCM extends ArrayList<Integer>{

    public int getLCM() {
        int lcm = 0;
        for (Iterator<Integer> iter = this.iterator(); iter.hasNext();) {
            int i = iter.next();
            if(lcm == 0) {
                lcm = i;
                continue;

            }
            else {
                lcm = LCMutils.lcm(lcm, i);
            }
        }
        return lcm;


    }


}
