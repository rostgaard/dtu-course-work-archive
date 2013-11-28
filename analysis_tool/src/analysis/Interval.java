package analysis;

public class Interval {

    int lowerValue;
    int upperValue;
    int minValue;
    int maxValue;
    Intervals exMin;
    Intervals exMax;

    public Interval setInterval(int minValue, int maxValue) {
        this.lowerValue = minValue;
        this.upperValue = maxValue;
        return this;
    }

    public Interval(IntervalLattice lattice) {
        this.minValue = lattice.Minimum;
        this.maxValue = lattice.Maximum;

        this.exMin = Intervals.IN_RANGE;
        this.exMax = Intervals.IN_RANGE;
    }

    @Override
    public String toString() {
        String buffer = "[";
        if (this.exMin == Intervals.IN_RANGE) {
            buffer += this.lowerValue;
        } else {
            buffer += this.exMin;
        }

        buffer += ";";

        if (this.exMax == Intervals.IN_RANGE) {
            buffer += this.upperValue;
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
        if (value == this.minValue) {
            return Intervals.MIN;
        } else if (value == this.maxValue) {
            return Intervals.MAX;
        } else if (value < this.minValue) {
            return Intervals.MINUS_INF;
        } else if (value > this.maxValue) {
            return Intervals.MAX;
        }

        return Intervals.IN_RANGE;
    }

    private void addition(Interval otherInterval) {

        this.lowerValue += otherInterval.lowerValue;
        this.upperValue += otherInterval.upperValue;

        updateRanges();
    }

    private void subtraction(Interval otherInterval) {

        this.lowerValue -= otherInterval.lowerValue;
        this.upperValue -= otherInterval.upperValue;

        updateRanges();
    }

    private void multiplication(Interval otherInterval) {

        this.lowerValue *= otherInterval.lowerValue;
        this.upperValue *= otherInterval.upperValue;

        updateRanges();
    }
    
    private void division(Interval otherInterval) {

        this.lowerValue /= otherInterval.lowerValue;
        this.upperValue /= otherInterval.upperValue;

        updateRanges();
    }

    private void updateRanges() {

        // Lower bound.
        if (!this.lowerBoundIsINF()) {
            if (this.lowerValue == this.minValue) {
                this.exMin = Intervals.MIN;
            } else if (this.lowerValue < this.minValue) {
                this.exMin = Intervals.MINUS_INF;
            }
        }

        // Upper bound.
        if (this.upperBoundIsINF()) {
            if (this.upperValue == this.maxValue) {
                this.exMax = Intervals.MAX;
            } else if (this.upperValue > this.maxValue) {
                this.exMax = Intervals.INF;
            }
        }
    }

    public Interval set(Interval interval) {
        this.lowerValue = interval.lowerValue;
        this.upperValue = interval.upperValue;

        this.minValue = interval.minValue;
        this.maxValue = interval.maxValue;

        this.updateRanges();

        return this;
    }

    /**
     * See table below.
     * 
     *                        rhs
     *     -----------------------------------------
     *     | lower |  INF  |  -INF |  MIN  |  Max  |
     *     -----------------------------------------
     *     |  INF  |   T
     *     --------
     * lhs | -INF  |
     *     ---------
     *     |  Min  |
     *     ----------
     *     |  Max  |
     *     ----------
     * 
     * @param otherInterval
     * @return 
     */
    public boolean subsetOf(Interval otherInterval) {
        if (this.exMin == Intervals.IN_RANGE
                && otherInterval.exMin == Intervals.IN_RANGE
                && this.exMax == Intervals.IN_RANGE
                && otherInterval.exMax == Intervals.IN_RANGE) {
            return (this.lowerValue >= otherInterval.lowerValue)
                    && (this.upperValue <= otherInterval.upperValue);
        }

        //TODO Handle extremum cases.

        return false;
    }

    public Interval merge(Interval otherInterval) {
        if (this.exMin == Intervals.IN_RANGE
                && otherInterval.exMin == Intervals.IN_RANGE) {

            this.lowerValue = Math.min(this.lowerValue, otherInterval.lowerValue);
        }

        if (this.exMax == Intervals.IN_RANGE
                && otherInterval.exMax == Intervals.IN_RANGE) {

            this.upperValue = Math.max(this.upperValue, otherInterval.upperValue);
        } else if (this.exMax == Intervals.INF
                || otherInterval.exMax == Intervals.INF) {
            this.exMax = Intervals.INF;
        } else if (this.exMin == Intervals.INF
                || otherInterval.exMin == Intervals.INF) {
            this.exMin = Intervals.INF;
        } else if (this.exMax == Intervals.MINUS_INF
                || otherInterval.exMax == Intervals.MINUS_INF) {
            this.exMax = Intervals.MINUS_INF;
        } else if (this.exMin == Intervals.MINUS_INF
                || otherInterval.exMin == Intervals.MINUS_INF) {
            this.exMin = Intervals.MINUS_INF;
        } else if (this.exMax == Intervals.MAX
                || otherInterval.exMax == Intervals.MAX) {
            this.exMax = Intervals.MAX;
        } else if (this.exMin == Intervals.MAX
                || otherInterval.exMin == Intervals.MAX) {
            this.exMin = Intervals.MAX;
        } else if (this.exMax == Intervals.MIN
                || otherInterval.exMax == Intervals.MIN) {
            this.exMax = Intervals.MIN;
        } else if (this.exMin == Intervals.MIN
                || otherInterval.exMin == Intervals.MIN) {
            this.exMin = Intervals.MIN;
        }

        return this;
    }
}
