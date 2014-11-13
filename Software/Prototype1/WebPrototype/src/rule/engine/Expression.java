/**
 * @author Kim Rostgaard Christensen - s084283
 */

package rule.engine;

abstract public class Expression {
	abstract boolean matches (Event event);
}
