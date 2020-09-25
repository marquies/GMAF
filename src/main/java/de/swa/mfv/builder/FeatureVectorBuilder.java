package de.swa.mfv.builder;

import java.io.File;

import de.swa.gmaf.plugin.ExifHandler;
import de.swa.mfv.FeatureVector;
import de.swa.mfv.Node;

public class FeatureVectorBuilder {
	public static FeatureVector getFeatureVectorFromAsset(File f) {
		return ExifHandler.getFeatureVectorFromExif(f);
	}
	
	public static void mergeIntoFeatureVector(FeatureVector base, FeatureVector delta) {
		Node n = base.getCurrentNode();
		for (Node ni : delta.getNodes()) n.addChildNode(ni);
	}
	
	public static FeatureVector generateFeatureVectorForAsset(String assetWithoutMfv) {
		return null;
	}
	
	public static FeatureVector unflatten(String s, Unflattener uf) {
		return uf.unflatten(s);
	}
	
	public static String flatten(FeatureVector fv, Flattener fs) {
		return fs.flatten(fv);
	}	
	
	public static String attach(String assetWithoutMfv, FeatureVector fv) {
		return "";	
	}
	
	public static FeatureVector parse(String meta) {
		return new FeatureVector();
	}
}
