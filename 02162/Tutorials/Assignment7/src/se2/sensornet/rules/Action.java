/**
 * @author Kim Rostgaard Christensen - s084283
 */

package se2.sensornet.rules;

import java.util.List;

public class Action {

	String actuator;
	String value;
	List<Expression> parameters;
	
	
	public Action(String actuator, String value, List<Expression> parameters) {
		super();
		this.actuator = actuator;
		this.value = value;
		this.parameters = parameters;
	}

	public String getActuator() {
		return actuator;
	}
	public void setActuator(String actuator) {
		this.actuator = actuator;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public List<Expression> getParameters() {
		return parameters;
	}
	public void setParameters(List<Expression> parameters) {
		this.parameters = parameters;
	}

	private String parameterString() {
		String buffer = "";
		
		for (Expression expr : this.parameters) {
			buffer += expr;
			if (this.parameters.indexOf(expr) != this.parameters.size()-1) {
				buffer += ", ";
			}
		}
		
		return buffer;
	}
	
	public String toString () {
		return this.actuator + "." + this.value + "(" + this.parameterString() +")";
	}
	
}
