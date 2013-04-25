package domain;

public class Node {

	private String id;
	private String name;

	public Node(String id, String name){
		this.id = id;
		this.name = name;
	}

	public String getId(){
		return id;
	}

	public String getName(){
		return name;
	}

	@Override
	public String toString() {
		if(name == null){
			return "Node " + id;
		}
		if(name.length() > 8){
			name = name.substring(0, 8);
		}
		if(id.length() > 2){
			id = id.substring(0, 2);
		}
		return name + " " + id;
	}

	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		if (obj instanceof Node) {
			Node that = (Node) obj;
			result = (this.getId() == that.getId() && this.getName() == that.getName());
		}
		return result;
	}

	@Override
	public int hashCode() {
		int hash = 1;
		hash = hash * 12 + id.hashCode();
		hash = hash * 19 + name.hashCode();
		return hash;
	}	
}
