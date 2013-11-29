package analysis;

public class Interval {

    IntervalLattice lattice;
    int lowerBound;
    int upperBound;
    Intervals exMin;
    Intervals exMax;

    public static Interval doubleInfinity(IntervalLattice lattice) {
        Interval retval = new Interval(lattice);

        retval.exMin = Intervals.MINUS_INF;
        retval.exMax = Intervals.INF;

        return retval;
    }

    public Interval setAbsoluteInterval(int newLowerBound, int newUpperBound) {
        this.lowerBound = newLowerBound;
        this.upperBound = newUpperBound;

        this.updateRanges();
        return this;
    }

    public Interval(IntervalLattice lattice) {
        this.lattice = lattice;

        this.exMin = Intervals.IN_RANGE;
        this.exMax = Intervals.IN_RANGE;

        this.updateRanges();
    }

    @Override
    public String toString() {

        String buffer = "[";
        if (this.exMin == Intervals.IN_RANGE) {
            buffer += this.lowerBound;
        } else {
            buffer += this.exMin;
        }

        buffer += ";";

        if (this.exMax == Intervals.IN_RANGE) {
            buffer += this.upperBound;
        } else {
            buffer += this.exMax;
        }

        return buffer + "]";
    }

    private boolean lowerBoundIsINF() {
        return (this.exMin == Intervals.INF) || (this.exMin == Intervals.MINUS_INF);
    }

    private boolean upperBoundIsINF() {
        return (this.exMax == Intervals.INF) || (this.exMax == Intervals.MINUS_INF);
    }

    private Intervals boundOf(int value) {
        if (value == this.lattice.Minimum) {
            return Intervals.MIN;
        } else if (value == this.lattice.Maximum) {
            return Intervals.MAX;
        } else if (value < this.lattice.Minimum) {
            return Intervals.MINUS_INF;
        } else if (value > this.lattice.Maximum) {
            return Intervals.MAX;
        }

        return Intervals.IN_RANGE;
    }

    public static Interval addition(Interval interval1, Interval interval2) {
        Interval retval = new Interval(interval1.lattice);

        retval.lowerBound = interval1.lowerBound + interval2.lowerBound;
        retval.upperBound = interval1.upperBound + interval2.upperBound;

        retval.updateRanges();

        return retval;
    }

    public static Interval subtraction(Interval interval1, Interval interval2) {
        Interval retval = new Interval(interval1.lattice);

        retval.lowerBound = interval1.lowerBound - interval2.lowerBound;
        retval.upperBound = interval1.upperBound - interval2.upperBound;

        retval.updateRanges();

        return retval;
    }

    public static Interval multiplication(Interval interval1, Interval interval2) {
        Interval retval = new Interval(interval1.lattice);

        retval.lowerBound = interval1.lowerBound * interval2.lowerBound;
        retval.upperBound = interval1.upperBound * interval2.upperBound;

        retval.updateRanges();

        return retval;
    }

    public static Interval division(Interval interval1, Interval interval2) {
        Interval retval = new Interval(interval1.lattice);

        retval.lowerBound = interval1.lowerBound / interval2.lowerBound;
        retval.upperBound = interval1.upperBound / interval2.upperBound;

        retval.updateRanges();

        return retval;
    }

    public static Interval negate(Interval interval) {
        Interval retval = new Interval(interval.lattice);

        retval.lowerBound = -interval.upperBound;
        retval.upperBound = -interval.lowerBound;

        retval.updateRanges();

        return retval;
    }

    private void updateRanges() {

        // Lower bound.
        if (!this.lowerBoundIsINF()) {
            if (this.lowerBound == this.lattice.Minimum) {
                this.exMin = Intervals.MIN;
            } else if (this.lowerBound < this.lattice.Minimum) {
                this.exMin = Intervals.MINUS_INF;
            }
        }

        // Upper bound.
        if (!this.upperBoundIsINF()) {
            if (this.upperBound == this.lattice.Maximum) {
                this.exMax = Intervals.MAX;
            } else if (this.upperBound > this.lattice.Maximum) {
                this.exMax = Intervals.INF;
            }
        }
    }

    public Interval set(Interval interval) {
        this.lowerBound = interval.lowerBound;
        this.upperBound = interval.upperBound;

        this.lattice = interval.lattice;

        this.updateRanges();

        return this;
    }

    /**
     * @param otherInterval
     * @return
     */
    public boolean subsetOf(Interval otherInterval) {
        return (this.lowerBound >= otherInterval.lowerBound
                && this.upperBound <= otherInterval.upperBound);
    }

    public Interval merge(Interval otherInterval) {

        this.lowerBound = Math.min(this.lowerBound, otherInterval.lowerBound);
        this.upperBound = Math.max(this.upperBound, otherInterval.upperBound);

        this.updateRanges();

        return this;
    }
}
