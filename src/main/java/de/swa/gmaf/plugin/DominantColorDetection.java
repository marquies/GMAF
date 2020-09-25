package de.swa.gmaf.plugin;

import java.awt.Color;
import java.util.Vector;

import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.ColorInfo;
import com.google.cloud.vision.v1.DominantColorsAnnotation;
import com.google.cloud.vision.v1.EntityAnnotation;
import com.google.cloud.vision.v1.Feature;
import com.google.cloud.vision.v1.Feature.Type;

import de.swa.mfv.CompositionRelationship;
import de.swa.mfv.FeatureVector;
import de.swa.mfv.Node;
import de.swa.mfv.TechnicalAttribute;

public class DominantColorDetection extends GoogleVisionBasePlugin {
	private Vector<Node> objects = new Vector<Node>();

	protected Feature getSearchFeature() {
		Feature f = Feature.newBuilder().setType(Type.IMAGE_PROPERTIES).build();
		return f;
	}

	protected void processResult(AnnotateImageResponse res, FeatureVector fv) {
		for (EntityAnnotation annotation : res.getLandmarkAnnotationsList()) {
			Node n = fv.getCurrentNode();
			String txt = annotation.getDescription();
			Node cn = new Node(txt);
			cn.addTechnicalAttribute(getBoundingBox(annotation.getBoundingPoly()));
			n.addChildNode(cn);
			objects.add(cn);
			cn.setDetectedBy(this.getClass().getName());
			cn.addCompositionRelationship(new CompositionRelationship(CompositionRelationship.RELATION_PART_OF, n));
			
	        TechnicalAttribute ts = new TechnicalAttribute();
			DominantColorsAnnotation colors = res.getImagePropertiesAnnotation().getDominantColors();
	        for (ColorInfo color : colors.getColorsList()) {
	        	int r = (int)(color.getColor().getRed());
	        	int g = (int)(color.getColor().getGreen());
	        	int b = (int)(color.getColor().getBlue());
		        ts.addDominantColor(new Color(r, g, b));
		        System.out.println("DOMINANT COLOR: " + r + " " + g + " " + b);
	        }
	        n.addTechnicalAttribute(ts);
		}
	}

	public boolean providesRecoursiveData() {
		return true;
	}
	
	public Vector<Node> getDetectedNodes() {
		return objects;
	}

}
