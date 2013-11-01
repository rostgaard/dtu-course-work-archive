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
}
