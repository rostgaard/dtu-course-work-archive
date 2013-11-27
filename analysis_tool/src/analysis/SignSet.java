package analysis;

import java.util.HashSet;

public class SignSet extends HashSet<Sign> {

    public SignSet() {
    }

    public SignSet put(Sign s) {
        if (!this.contains((Sign) s)) {
            this.add(s);
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
