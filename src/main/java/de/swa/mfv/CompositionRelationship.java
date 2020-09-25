package de.swa.mfv;

public class CompositionRelationship {
	public static final int RELATION_NEXT_TO = 0;
	public static final int RELATION_BEFORE = 1;
	public static final int RELATION_BEHIND = 2;
	public static final int RELATION_ABOVE = 3;
	public static final int RELATION_UNDER = 4;
	public static final int RELATION_PART_OF = 5;
	public static final int RELATION_ATTACHED_TO = 6;
	
	private int type;
	private transient Node relatedObject;
	
	public CompositionRelationship() {}
	public CompositionRelationship(int type, Node n) {
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
