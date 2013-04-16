package dk.retrospekt.dtemp.vectorclock;

import java.io.Serializable;

/*
 * Class implementing vector clocks.
 */

/**
 *
 * @author Kim Rostgaard Christensen
 */
public class VectorClock implements Serializable {

    private int[] Clock;

    /**
     * Constructs a VectorClock with {@code numNodes} elements. The VectorClock
     * is of fixed size and cannot be extended after construction.
     *
     * @param numNodes The number of processes to maintain a state for.
     */
    public VectorClock(int numNodes) {
        this.Clock = new int[numNodes];

        // Initialize.
        for (int i : this.Clock) {
            i = 0;
        }
    }

    /**
     * Increments the clock of the pid supplied.
     *
     * @param pid The ID of the process which was incremented.
     */
    public void incrementClock(int pid) {
        this.Clock[pid]++;
    }

    /**
     * Stringify the Vector clock. Debug-friendly representation of the vector
     * clock.
     *
     * @return String representation of the Vector clock.
     */
    @Override
    public String toString() {
        String buffer = new String("<");

        if (Clock.length == 0) {
            buffer += "empty";
        } else {
            for (int i = 0; i < Clock.length; i++) {
                buffer += new Integer(Clock[i]).toString();
                if (i + 1 != Clock.length) {
                    buffer += ",";
                }
            }
        }

        return buffer + ">";

    }
}
