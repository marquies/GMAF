package de.swa.mfv;

import java.util.UUID;
import java.util.Vector;

public class Node {
	private String name;
	private Vector<Node> childNodes = new Vector<Node>();
	private Vector<Weight> weights = new Vector<Weight>();
	private Vector<TechnicalAttribute> technicalAttributes = new Vector<TechnicalAttribute>();
	private Vector<SemanticRelationship> semanticRelationships = new Vector<SemanticRelationship>();
	private Vector<CompositionRelationship> compositionRelationships = new Vector<CompositionRelationship>();
	private Vector<AssetLink> assetLinks = new Vector<AssetLink>();
	private String detectedBy;
	
	public Node() {}
	public Node(String name) {
		setName(name);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String n) {
		name = n;
		weights.add(new Weight(Context.getDefaultContext(), 1.0f));
	}
	
	public void addChildNode(Node n) {
		childNodes.add(n);
	}
	
	public Vector<Node> getChildNodes() {
		return childNodes;
	}
	
	public void addWeight(Weight w) {
		weights.add(w);
	}
	
	public Vector<Weight> getWeights() {
		return weights;
	}
	
	public void addTechnicalAttribute(TechnicalAttribute ta) {
		technicalAttributes.add(ta);
	}
	
	public Vector<TechnicalAttribute> getTechnicalAttributes() {
		return technicalAttributes;
	}
	
	public void addSemanticRelationship(SemanticRelationship sr) {
		semanticRelationships.add(sr);
	}
	
	public Vector<SemanticRelationship> getSemanticRelationships() {
		return semanticRelationships;
	}
	
	public void addCompositionRelationship(CompositionRelationship cr) {
		compositionRelationships.add(cr);
	}
	
	public Vector<CompositionRelationship> getCompositionRelationships() {
		return compositionRelationships;
	}
	
	public void addAssetLink(AssetLink al) {
		assetLinks.add(al);
	}
	
	public Vector<AssetLink> getAssetLinks() {
		return assetLinks;
	}
	public String getDetectedBy() {
		return detectedBy;
	}
	public void setDetectedBy(String detectedBy) {
		this.detectedBy = detectedBy;
	}
}
