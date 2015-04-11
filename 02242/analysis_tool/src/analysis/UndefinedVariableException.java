package analysis;

/**
 *
 * @author krc
 */
public class UndefinedVariableException extends IllegalStateException {
    
    public UndefinedVariableException (String message) {
        super (message);
    }
}
