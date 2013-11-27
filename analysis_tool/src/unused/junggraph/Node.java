package unused.junggraph;

//import java.awt.Color;

/**
 * 
 * Class for a node
 * Add color property if needed
 *
 */

public class Node {

	private String id;
	private String name;
//	private Color color;

	/**
	 * Constructor
	 * @param id - id of the node
	 * @param name - name of the node
	 */
	public Node(String id, String name){
		this.id = id;
		this.name = name;
//		this.color = Color.WHITE; //default color of the nodes
	}

	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id = id;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
//	public Color getColor(){
//		return color;
//	}
	
//	public void setColor(Color color){
//		this.color = color;
//	}

	@Override
	public String toString() {
		//the cuttings are done due to space on the GUI and readability
		String tempId = id;
		String tempName = name;
		
		//cut id if longer than 2 chars
		if(tempId.length() > 2){
			tempId = tempId.substring(0, 2);
		}		
		//if no name declared, then return id
		if(tempName == null){
			return "Node " + tempId;
		}		
		//cut name if longer than 8 chars
		if(tempName.length() > 8){
			tempName= tempName.substring(0, 8);
		}
		return tempName + " " + tempId;
	}

	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		if (obj instanceof Node) {
			Node that = (Node) obj;
			result = (this.getId() == that.getId() && this.getName() == that.getName() /*&& this.getColor() == that.getColor()*/);
		}
		return result;
	}

	@Override
	public int hashCode() {
		int hash = 1;
		hash = hash * 12 + id.hashCode();
		hash = hash * 19 + name.hashCode();
//		hash = hash * 4 + color.hashCode();
		return hash;
	}	
}
