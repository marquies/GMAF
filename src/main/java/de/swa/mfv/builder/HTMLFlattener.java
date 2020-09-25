package de.swa.mfv.builder;

import de.swa.mfv.FeatureVector;
import de.swa.mfv.Node;

public class HTMLFlattener implements Flattener{
	public String flatten(FeatureVector fv) {
		StringBuffer sb = new StringBuffer();
		for (Node n : fv.getNodes()) process(n, sb, "");
		return sb.toString();
	}
	
	private void process(Node n, StringBuffer sb, String offset) {
		sb.append("<br>" + offset + "Node: " + n.getName());
		for (Node ni : n.getChildNodes()) process(ni, sb, offset + "&nbsp;&nbsp;");
	}
}
