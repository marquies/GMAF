package de.swa.mfv;

import java.net.URL;
import java.util.Vector;

public class FeatureVector {
	private Vector<Node> nodes = new Vector<Node>();
	private GeneralMetadata generalMetadata;
	private Security security;
	private Vector<Location> locations = new Vector<Location>();
	private Node currentNode;

	public void addNode(Node n) {
		nodes.add(n);
	}

	public Vector<Node> getNodes() {
		return nodes;
	}

	public void addLocation(Location loc) {
		for (Location l : locations) {
			if (l.getName().equals(loc.getName()))
				return;
		}
		locations.add(loc);
	}

	public Vector<Location> getLocations() {
		return locations;
	}

	public GeneralMetadata getGeneralMetadata() {
		return generalMetadata;
	}

	public void setGeneralMetadata(GeneralMetadata generalMetadata) {
		this.generalMetadata = generalMetadata;
	}

	public Security getSecurity() {
		return security;
	}

	public void setSecurity(Security security) {
		this.security = security;
	}

	public Node getCurrentNode() {
		return currentNode;
	}

	public void setCurrentNode(Node currentNode) {
		this.currentNode = currentNode;
	}

}
