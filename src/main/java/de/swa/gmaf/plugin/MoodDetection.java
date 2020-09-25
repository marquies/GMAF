package de.swa.gmaf.plugin;

import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.FaceAnnotation;
import com.google.cloud.vision.v1.Feature;
import com.google.cloud.vision.v1.Feature.Type;
import com.google.cloud.vision.v1.Likelihood;

import de.swa.mfv.CompositionRelationship;
import de.swa.mfv.FeatureVector;
import de.swa.mfv.Node;

public class MoodDetection extends GoogleVisionBasePlugin {
	protected Feature getSearchFeature() {
		Feature f = Feature.newBuilder().setType(Type.FACE_DETECTION).build();
		return f;
	}

	protected void processResult(AnnotateImageResponse res, FeatureVector fv) {
		for (FaceAnnotation annotation : res.getFaceAnnotationsList()) {
			Node n = fv.getCurrentNode();
			String label = "";
			System.out.println("Joy:" + annotation.getJoyLikelihoodValue());
			System.out.println("Sorrow: " + annotation.getSorrowLikelihoodValue());
			System.out.println("Anger: " + annotation.getAngerLikelihoodValue());
			
			if (annotation.getJoyLikelihood().getNumber() > Likelihood.LIKELY.getNumber()) label = "Joy";
			if (annotation.getSorrowLikelihood().getNumber() > Likelihood.LIKELY.getNumber()) label = "Sorrow";
			if (annotation.getAngerLikelihood().getNumber() > Likelihood.LIKELY.getNumber()) label = "Anger";
			if (annotation.getSurpriseLikelihood().getNumber() > Likelihood.LIKELY.getNumber()) label = "Surprise";
			System.out.println("MOOD DETECTED: " + label);
			Node cn = new Node(label);
			n.addChildNode(cn);
			cn.setDetectedBy(this.getClass().getName());
			cn.addCompositionRelationship(new CompositionRelationship(CompositionRelationship.RELATION_PART_OF, n));
		}
	}

	public boolean providesRecoursiveData() {
		return false;
	}
}
