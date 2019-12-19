package graphAs;

public class Edge {
	Node endNode;
	String label;
	Edge(Node end, String label){
        this.endNode=end;
        this.label=label;
    }
	public Node getEndNode() {
		return endNode;
	}
	public void setEndNode(Node endNode) {
		this.endNode = endNode;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
}
