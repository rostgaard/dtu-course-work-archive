package rule.engine;

import java.util.HashMap;

public class Event {
	public String type;
	public String sensor;
	public String key;
	public String value;
	
	private HashMap<String, Integer> attributes = new HashMap<>();
	
	public void addAttribute (String key, int value) {
		this.attributes.put(key.toLowerCase(), value);
	}
	
	public Integer getAttributeValue (String key) {
		if (!this.attributes.containsKey(key.toLowerCase())) {
			return null;
		} else {
			return (this.attributes.get(key.toLowerCase()));
		}
	}
	
}
