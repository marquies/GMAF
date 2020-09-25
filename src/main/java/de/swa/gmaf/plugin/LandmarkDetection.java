package de.swa.gmaf.plugin;

import java.util.Vector;

import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.EntityAnnotation;
import com.google.cloud.vision.v1.Feature;
import com.google.cloud.vision.v1.Feature.Type;

import de.swa.mfv.CompositionRelationship;
import de.swa.mfv.FeatureVector;
import de.swa.mfv.Node;

public class LandmarkDetection extends GoogleVisionBasePlugin {
	private Vector<Node> objects = new Vector<Node>();

	protected Feature getSearchFeature() {
		Feature f = Feature.newBuilder().setType(Type.LANDMARK_DETECTION).build();
		return f;
	}

	protected void processResult(AnnotateImageResponse res, FeatureVector fv) {
		for (EntityAnnotation annotation : res.getLandmarkAnnotationsList()) {
			Node n = fv.getCurrentNode();
			String txt = annotation.getDescription();
			System.out.println("LANDMARK DETECTED: " + txt);
			Node cn = new Node(txt);
			cn.addTechnicalAttribute(getBoundingBox(annotation.getBoundingPoly()));
			n.addChildNode(cn);
			objects.add(cn);
			cn.setDetectedBy(this.getClass().getName());
			cn.addCompositionRelationship(new CompositionRelationship(CompositionRelationship.RELATION_PART_OF, n));
		}
	}

	public boolean providesRecoursiveData() {
		return true;
	}
	
	public Vector<Node> getDetectedNodes() {
		return objects;
	}

}
