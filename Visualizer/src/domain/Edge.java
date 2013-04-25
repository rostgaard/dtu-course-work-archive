package domain;

public class Edge {

	private Node n1;
	private Node n2;

	public Edge(Node n1, Node n2){
		this.n1 = n1;
		this.n2 = n2;
	}

	public Node getN1() {
		return n1;
	}

	public Node getN2() {
		return n2;
	}

	@Override
	public String toString() {
		return "Edge [n1=" + n1 + ", n2=" + n2 + "]";
	}

	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		if (obj instanceof Edge) {
			Edge that = (Edge) obj;
			result = (this.getN1() == that.getN1() && this.getN2() == that.getN2());
		}
		return result;
	}

	@Override
	public int hashCode() {
		int hash = 1;
		hash = hash * 17 + (n1 == null ? 0 : n1.hashCode());
		hash = hash * 13 + (n2 == null ? 0 : n2.hashCode());
		return hash;
	}
}