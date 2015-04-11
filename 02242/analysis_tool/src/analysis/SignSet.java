package analysis;

import static analysis.Sign.*;
import java.util.HashSet;

public class SignSet extends HashSet<Sign> implements Comparable<SignSet> {

    public static final SignSet p = new SignSet().put(Sign.P);
    public static final SignSet z = new SignSet().put(Sign.Z);
    public static final SignSet n = new SignSet().put(Sign.N);
    public static final SignSet pz = new SignSet().put(Sign.P, Sign.Z);
    public static final SignSet p_n = new SignSet().put(Sign.P, Sign.N);
    public static final SignSet pnz = new SignSet().put(Sign.P, Sign.Z, Sign.N);
    public static final SignSet nz = new SignSet().put(Sign.Z, Sign.N);
    public static final SignSet empty = new SignSet();

    public static int indexOf(Sign s) {
        switch (s) {
            case N:
                return 0;
            case Z:
                return 1;
            case P:
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

    public boolean equals(SignSet set) {
        return this.subsetOf(set) && set.subsetOf(this);
    }

    @Override
    public int compareTo(SignSet set) {
        if (this.equals(set)) {
            return 0;
        } else {
            return 1;
        }
    }

    public SignSet intersect(SignSet signSet) {
        SignSet selfCopy = new SignSet();
        selfCopy.addAll(this);
        for(Sign sign : this) {
            if (!signSet.contains(sign)) {
                selfCopy.remove(sign);
            }
        }

        return selfCopy;

    }
}
