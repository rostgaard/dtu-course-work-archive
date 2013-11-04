package syntaxtree.condition;

/**
 * Data representation for the boolean value "true"
 *
 */
public class TrueCondition extends Condition{

	private boolean truee = true;
		
	public boolean getTrue(){
		return truee;
	}

	public boolean isTrue() {
		return truee;
	}

	public void setTrue(boolean truee) {
		this.truee = truee;
	}
	
	@Override
	public String toString() {
		return "\nClass: " + getClass().getSimpleName() + "\nValue: " + truee + "\n";
	}
}
