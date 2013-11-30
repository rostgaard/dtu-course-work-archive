package analysis;


import java.util.HashSet;


public class BoolSet extends HashSet<Bool> {

    public static final BoolSet ttff = new BoolSet().put(Bool.TT,Bool.FF);
    public static final BoolSet tt = new BoolSet().put(Bool.TT);
    public static final BoolSet ff = new BoolSet().put(Bool.FF);


    public BoolSet put(Bool... bools) {
        for (Bool bool : bools) {
            if (!this.contains((bool))) {
                this.add(bool);
            }
        }
        return this;
    }

    public Boolean containsTrue() {
        return this.contains(Bool.TT);
    }

    public static final BoolSet[][] equalMatrix = {
            {ttff, ff, ff},
            {ff, tt, ff},
            {ff, ff, ttff}
    };

    public static final BoolSet[][] notEqualMatrix = {
            {ttff, tt, tt},
            {tt, ff, tt},
            {tt, tt, ttff}
    };

    public static final BoolSet[][] greaterThanEqualMatrix = {
            {ttff, ff, ff},
            {tt, tt, ff},
            {tt, tt, ttff}
    };

    public static final BoolSet[][] lessThanEqualMatrix = {
            {ttff, tt, tt},
            {ff, tt, ff},
            {ff, ff, ttff}
    };

    public static final BoolSet[][] greaterThanMatrix = {
            {ttff, ff, ff},
            {tt, ff, ff},
            {tt, tt, ttff}
    };

    public static final BoolSet[][] lessThanMatrix = {
            {ttff, tt, tt},
            {ff, ff, tt},
            {ff, ff, ttff}
    };

}
