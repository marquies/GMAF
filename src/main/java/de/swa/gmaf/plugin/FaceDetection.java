package de.swa.gmaf.plugin;

import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.FaceAnnotation;
import com.google.cloud.vision.v1.Feature;
import com.google.cloud.vision.v1.Feature.Type;

import de.swa.mfv.CompositionRelationship;
import de.swa.mfv.FeatureVector;
import de.swa.mfv.Node;

public class FaceDetection extends GoogleVisionBasePlugin {
	protected Feature getSearchFeature() {
		Feature f = Feature.newBuilder().setType(Type.FACE_DETECTION).build();
		return f;
	}

	protected void processResult(AnnotateImageResponse res, FeatureVector fv) {
		int counter = 1;
		
		for (FaceAnnotation annotation : res.getFaceAnnotationsList()) {
			Node n = fv.getCurrentNode();
			System.out.println("FACE DETECTED ");
			String txt = "Face";
			for (Node ci : n.getChildNodes()) {
				if (ci.getName().equals(txt)) {
					txt = txt + "_" + counter;
					counter ++;
				}
			}
			Node cn = new Node(txt);
			n.addChildNode(cn);
			cn.setDetectedBy(this.getClass().getName());
			cn.addCompositionRelationship(new CompositionRelationship(CompositionRelationship.RELATION_PART_OF, n));
		}
	}
	
	public boolean providesRecoursiveData() {
		return false;
	}
}
