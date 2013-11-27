package analysis;

public class Interval {

    int lowerValue;
    int upperValue;
    int minValue;
    int maxValue;
    IntervalExtremes exMin;
    IntervalExtremes exMax;

    public Interval setInterval(int minValue, int maxValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        return this;
    }

    public Interval(int minimumValue, int maximumValue) {
        this.minValue = minimumValue;
        this.maxValue = maximumValue;
        
        this.exMin = IntervalExtremes.IN_RANGE;
        this.exMax = IntervalExtremes.IN_RANGE;

    }

    public Interval put(Sign s) {
//        if (!this.contains((Sign) s)) {
//            this.add(s);
//        }
        return this;
    }

    public boolean subsetOf(Interval otherInterval) {
        if (this.exMin == IntervalExtremes.IN_RANGE
                && otherInterval.exMin == IntervalExtremes.IN_RANGE
                && this.exMax == IntervalExtremes.IN_RANGE
                && otherInterval.exMax == IntervalExtremes.IN_RANGE) {
            return (this.minValue >= otherInterval.minValue) &&
                    (this.maxValue <= otherInterval.maxValue);
        }
        
        //TODO Handle extremum cases.

        return false;
    }

    public Interval merge(Interval otherInterval) {
        if (this.exMin == IntervalExtremes.IN_RANGE
                && otherInterval.exMin == IntervalExtremes.IN_RANGE) {

            this.minValue = Math.min(this.minValue, otherInterval.minValue);
        }

        if (this.exMax == IntervalExtremes.IN_RANGE
                && otherInterval.exMax == IntervalExtremes.IN_RANGE) {

            this.maxValue = Math.max(this.maxValue, otherInterval.maxValue);
        }

        else if (this.exMax == IntervalExtremes.INF || 
                otherInterval.exMax == IntervalExtremes.INF) {
            this.exMax = IntervalExtremes.INF;
        }

        else if (this.exMin == IntervalExtremes.INF || 
                otherInterval.exMin == IntervalExtremes.INF) {
            this.exMin = IntervalExtremes.INF;
        }
        
        else if (this.exMax == IntervalExtremes.MINUS_INF || 
                otherInterval.exMax == IntervalExtremes.MINUS_INF) {
            this.exMax = IntervalExtremes.MINUS_INF;
        }

        else if (this.exMin == IntervalExtremes.MINUS_INF || 
                otherInterval.exMin == IntervalExtremes.MINUS_INF) {
            this.exMin = IntervalExtremes.MINUS_INF;
        }
        else if (this.exMax == IntervalExtremes.MAX || 
                otherInterval.exMax == IntervalExtremes.MAX) {
            this.exMax = IntervalExtremes.MAX;
        }

        else if (this.exMin == IntervalExtremes.MAX || 
                otherInterval.exMin == IntervalExtremes.MAX) {
            this.exMin = IntervalExtremes.MAX;
        }
        
        else if (this.exMax == IntervalExtremes.MIN || 
                otherInterval.exMax == IntervalExtremes.MIN) {
            this.exMax = IntervalExtremes.MIN;
        }

        else if (this.exMin == IntervalExtremes.MIN || 
                otherInterval.exMin == IntervalExtremes.MIN) {
            this.exMin = IntervalExtremes.MIN;
        }

        return this;
    }
}
