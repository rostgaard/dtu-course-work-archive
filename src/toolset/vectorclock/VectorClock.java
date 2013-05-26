/*
 * Class implementing vector clocks.
 */
package toolset.vectorclock;

import java.io.Serializable;

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
        assert (pid < this.Clock.length);

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
        String buffer = "";

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

        return "<" + buffer + ">";

    }

    /**
     * Returns a new VectorClock with the maximum values of each index. This
     * method is used to implement the merge operation.
     *
     * @param vc The VectorClock to make comparison to.
     * @return
     */
    private VectorClock max(VectorClock vc) {
        VectorClock tmp = new VectorClock(this.Clock.length);

        assert (this.Clock.length == vc.Clock.length);

        for (int i = 0; i < Clock.length; i++) {
            if (this.Clock[i] > vc.Clock[i]) {
                tmp.Clock[i] = this.Clock[i];
            } else {
                tmp.Clock[i] = vc.Clock[i];
            }
        }
        return tmp;
    }

    /**
     * Implements the merge operation (VC4) of vector clocks. Merges the
     * VectorClock with another, giving out the maximums of each index.

     *
     * @param vc The VectorClock to merge.
     */
    public void merge(VectorClock vc) {
        this.Clock = this.max(vc).Clock;
    }

    /**
     * VectorClock compare operation. 
     *
     * @param vc - VectorClock to compare to.
     *
     * @return VectorClockEquality value indicating how the two clocks relate to
     * each other.
     */
    public VectorClockEquality relativeTo(VectorClock vc) {
        // Initial "pull-down" flags.
        boolean foundEqual = true;

        boolean leftHasGreater = false;
        boolean rightHasGreater = false;

        assert (this.Clock.length == vc.Clock.length);


        // Go over all elements in Clock one.
        //for (String lEntry : pOne.keySet()) {
        for (int i = 0; i < this.Clock.length; i++) {
            // Compare if also present in clock two.

            // If any value differs, the two clocks are not equal.
            if (this.Clock[i] == vc.Clock[i]) {
                continue;
            }

            foundEqual = false;

            if (this.Clock[i] > vc.Clock[i]) {
                rightHasGreater = true;
            }

            if (this.Clock[i] < vc.Clock[i]) {
                leftHasGreater = true;
            }

        }
        // Return based on determined information.
        if (foundEqual) {
            return VectorClockEquality.EQUAL;
        }

        if (leftHasGreater && rightHasGreater) {
            return VectorClockEquality.CONCURRENT;
        }

        if (leftHasGreater) {
            return VectorClockEquality.GREATER;
        }

        if (rightHasGreater) {
            return VectorClockEquality.LESS;
        }

        return VectorClockEquality.INVALID;
    }
}
