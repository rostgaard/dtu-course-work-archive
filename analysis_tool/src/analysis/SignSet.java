package analysis;

import static analysis.Sign.*;
import java.util.HashSet;

public class SignSet extends HashSet<Sign> {

    public static final SignSet p = new SignSet().put(Sign.POSTIVE);
    public static final SignSet z = new SignSet().put(Sign.ZERO);
    public static final SignSet n = new SignSet().put(Sign.NEGATIVE);
    public static final SignSet pz = new SignSet().put(Sign.POSTIVE, Sign.ZERO);
    public static final SignSet p_n = new SignSet().put(Sign.POSTIVE, Sign.NEGATIVE);
    public static final SignSet pnz = new SignSet().put(Sign.POSTIVE, Sign.ZERO, Sign.NEGATIVE);
    public static final SignSet nz = new SignSet().put(Sign.ZERO, Sign.NEGATIVE);
    public static final SignSet empty = new SignSet();

    public static int indexOf(Sign s) {
        switch (s) {
            case NEGATIVE:
                return 0;
            case ZERO:
                return 1;
            case POSTIVE:
                return 2;
        }
        return -1;
    }
    public static final SignSet[][] plusMatrix = {
        {n, n, pnz},
        {n, z, p},
        {pnz, p, p}
    };
    public static final SignSet[][] subtractionMatrix = {
        {pnz, n, n},
        {n, z, n},
        {p, p, pnz}
    };
    public static final SignSet[][] multiplicationMatrix = {
        {p, z, n},
        {z, z, z},
        {n, z, p}
    };
    public static final SignSet[][] divisionMatrix = {
        {p, empty, n},
        {z, empty, z},
        {n, empty, p}
    };

    public SignSet put(Sign... signs) {
        for (Sign sign : signs) {
            if (!this.contains((sign))) {
                this.add(sign);
            }
        }
        return this;
    }

    public boolean subsetOf(SignSet otherSet) {
        for (Sign s : this) {
            if (!otherSet.contains(s)) {
                return false;
            }
        }

        return true;
    }

    public void merge(SignSet otherSet) {
        for (Sign s : otherSet) {
            if (!this.contains(s)) {
                this.add(s);
            }
        }
    }
}
