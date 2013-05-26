/*
 * Container for storing measurements locally on the admin.
 */
package temperaturemonitoring;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Logger;

/**
 *
 * @author Kim Rostgaard Christensen
 */
public class TemperatureMeasurementCollection {

    ArrayList<TempList> storage;
    private long version = 0;
    private static final Logger logger = Logger.getLogger(TemperatureMeasurementCollection.class.getName());
    int size;

    /**
     *
     * @param t
     * @return
     */
    public TemperatureMeasurementCollection(int size) {
        this.storage = new ArrayList<>();
        this.size = size;
        // Initialize
        for (int i = 0; i < this.size; i++) {
            this.storage.add(i, new TempList());
        }
    }

    public boolean add(int pid, Temperature t) {
        //if (!this.storage.contains(pid)) {
            this.storage.add(pid, new TempList());
        //}

        this.storage.get(pid).add(t);
            this.version++;
        
        return true;
    }

    private double calculateAverage(LinkedList<Double> marks) {
        Double sum = 0.0;
        for (Double mark : marks) {
            sum += mark;
        }
        return sum.doubleValue() / marks.size();
    }

    public double latestAverage() {
       
        LinkedList<Double> measurements = new LinkedList<>();

        // Gather all measurements with the same average.

        if (this.storage.get(0).isEmpty()) {
            return Double.NaN;
        }

        long commonPeriod = this.storage.get(0).get(this.storage.get(0).size() - 1).period();
        for (int i = 0; i < this.size; i++) {
            
            TempList list = this.storage.get(i);

            if (list.isEmpty()) { // Any list is empty.
                return Double.NaN;
            } else if (list.get(list.size() - 1).period() <= commonPeriod) {
                commonPeriod = list.get(list.size() - 1).period();
                }
            
        }
        
        for (int i = 0; i < this.size; i++) {

            TempList list = this.storage.get(i);

            for (Temperature measurement : list) {
                if (measurement.period() == commonPeriod) {
                    measurements.add(measurement.temperature());
                }
            }
            
        }

        return this.calculateAverage(measurements);
    }
}

class TempList extends ArrayList<Temperature> {

}
