package syntaxtree;

import analysis.BoolSet;
import analysis.SignSet;

/**
 * Data representation for supported relational operations
 *
 */
public enum RelationOperation {

	GREATERTHAN, LESSTHAN, GREATEREQUALTHAN, LESSEQUALTHAN, EQUAL, NOTEQUAL;

    public RelationOperation switchOperator() {
        switch (this) {
            case GREATERTHAN:
                return LESSEQUALTHAN;
            case LESSTHAN:
               return GREATEREQUALTHAN;
            case GREATEREQUALTHAN:
                return LESSTHAN;
            case LESSEQUALTHAN:
                return GREATERTHAN;
            case EQUAL:
                return NOTEQUAL;
            case NOTEQUAL:
                return EQUAL;
        }

        return this;
    }

    public BoolSet[][] getMatrix() {
        switch (this) {
            case GREATERTHAN:
                return BoolSet.greaterThanMatrix;
            case LESSTHAN:
                return BoolSet.lessThanMatrix;
            case GREATEREQUALTHAN:
                return BoolSet.greaterThanEqualMatrix;
            case LESSEQUALTHAN:
                return BoolSet.lessThanEqualMatrix;
            case EQUAL:
                return BoolSet.equalMatrix;
            case NOTEQUAL:
                return BoolSet.notEqualMatrix;
        }

        return null;
    }


}
