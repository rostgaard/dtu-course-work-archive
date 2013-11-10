package syntaxtree.condition;

/**
 * Data representation for the boolean value "false"
 *
 */
public class FalseCondition extends Condition{
	
	private boolean falsee = false;

	public boolean getFalse(){
		return falsee;
	}

	public boolean isFalse() {
		return falsee;
	}

	public void setFalse(boolean falsee) {
		this.falsee = falsee;
	}
	
	public String debugInformation() {
		return "\nClass: " + getClass().getSimpleName() + "\nValue: " + falsee + "\n";
	}
        
        @Override
        public String toString(){
            return "false";
        }
}
