package de.swa.gmaf.plugin;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Vector;

import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.Feature;
import com.google.cloud.vision.v1.Feature.Type;
import com.google.cloud.vision.v1.LocalizedObjectAnnotation;

import de.swa.mfv.CompositionRelationship;
import de.swa.mfv.FeatureVector;
import de.swa.mfv.Node;
import de.swa.mfv.SemanticRelationship;

public class ObjectLocalisation extends GoogleVisionBasePlugin {
	private Vector<Node> objects = new Vector<Node>();
	
	protected Feature getSearchFeature() {
		Feature f = Feature.newBuilder().setType(Type.OBJECT_LOCALIZATION).build();
		return f;
	}

	protected void processResult(AnnotateImageResponse res, FeatureVector fv) {
		int counter = 1;
		for (LocalizedObjectAnnotation annotation : res.getLocalizedObjectAnnotationsList()) {
			Node n = fv.getCurrentNode();
			String txt = annotation.getName();
			String mid = annotation.getMid();
			
			System.out.println("OBJECT DETECTED: " + txt);
			
			for (Node ci : n.getChildNodes()) {
				if (ci.getName().equals(txt)) {
					txt = txt + "_" + counter;
					counter ++;
				}
			}
			
			Node cn = new Node(txt);
			System.out.println("Node " + txt);
			cn.addTechnicalAttribute(getBoundingBox(annotation.getBoundingPoly()));
			n.addChildNode(cn);
			objects.add(cn); 
			cn.setDetectedBy(this.getClass().getName());
			if (!mid.equals("")) {
				URL url = null;
				try {
					url = new URL("https://kgsearch.googleapis.com/v1/id/" + mid);
				} catch (MalformedURLException e) {
				}
				cn.addSemanticRelationship(new SemanticRelationship(url, "Google Knowledge Graph ID: " + mid));
			}
		}
	}
		
	public Vector<Node> getDetectedNodes() {
		return objects;
	}
	
	public boolean providesRecoursiveData() {
		return true;
	}
}
