/**
 * @author Kim Rostgaard Christensen - s084283
 */

package se2.sensornet.rules;

abstract public class Expression {
	abstract boolean matches (Event event);
}
