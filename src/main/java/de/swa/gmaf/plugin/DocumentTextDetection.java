package de.swa.gmaf.plugin;

import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.EntityAnnotation;
import com.google.cloud.vision.v1.Feature;
import com.google.cloud.vision.v1.Feature.Type;

import de.swa.mfv.CompositionRelationship;
import de.swa.mfv.FeatureVector;
import de.swa.mfv.Node;

public class DocumentTextDetection extends GoogleVisionBasePlugin {
	protected Feature getSearchFeature() {
		Feature f = Feature.newBuilder().setType(Type.TEXT_DETECTION).build();
		return f;
	}

	protected void processResult(AnnotateImageResponse res, FeatureVector fv) {
		for (EntityAnnotation annotation : res.getTextAnnotationsList()) {
			Node n = fv.getCurrentNode();
			String txt = annotation.getDescription();
			System.out.println("TEXT DETECTED: " + txt);
			Node cn = new Node(txt);
			cn.addTechnicalAttribute(getBoundingBox(annotation.getBoundingPoly()));
			n.addChildNode(cn);
			cn.setDetectedBy(this.getClass().getName());
			cn.addCompositionRelationship(new CompositionRelationship(CompositionRelationship.RELATION_PART_OF, n));
		}
	}

	public boolean providesRecoursiveData() {
		return false;
	}
}
