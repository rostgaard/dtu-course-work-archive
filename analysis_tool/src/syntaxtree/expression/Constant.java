package syntaxtree.expression;

/**
 * Data representation for constants
 *
 */
public class Constant extends Expression{

	private final int n;
	
	public Constant(int n){
		this.n = n;
	}

	public int getN() {
		return n;
	}

	public String debugInformation() {
		return "\nClass: " + getClass().getSimpleName() +"\nValue: " + n + "\n";
        }

        @Override
        public String toString() {
            return ""+n;
        }
}
