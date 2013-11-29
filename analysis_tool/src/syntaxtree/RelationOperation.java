package syntaxtree;

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

}
