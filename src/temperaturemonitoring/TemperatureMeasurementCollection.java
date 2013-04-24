/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package temperaturemonitoring;

import java.util.ArrayList;

/**
 *
 * @author Kim Rostgaard Christensen
 */
public class TemperatureMeasurementCollection extends ArrayList<Temperature> {

    private long version = 0;

    /**
     *
     * @param t
     * @return
     */
    @Override
    public boolean add(Temperature t) {
        boolean retVal = super.add(t);

        if (retVal) {
            this.version++;
        }
        
        return retVal;
    }
}
