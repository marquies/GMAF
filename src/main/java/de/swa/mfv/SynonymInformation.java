package de.swa.mfv;

public class SynonymInformation {
	private static final int TYPE_SYNONYM = 0;
	private static final int TYPE_AGGREGATION = 1;
	private static final int TYPE_GENERALISATION = 2;
	
	private int type;
	private Node relatedObject;
	
	public SynonymInformation() {}
	public SynonymInformation(int type, Node n) {
		this.type = type;
		relatedObject = n;
	}
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Node getRelatedObject() {
		return relatedObject;
	}
	public void setRelatedObject(Node relatedObject) {
		this.relatedObject = relatedObject;
	}
}
