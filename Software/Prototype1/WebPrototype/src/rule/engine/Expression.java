/**
 * @author stefan
 * @author Kim Rostgaard Christensen - s084283
 */

package rule.engine;

import dto.model.Event;

abstract public class Expression {
	abstract boolean matches (Event event);
}
