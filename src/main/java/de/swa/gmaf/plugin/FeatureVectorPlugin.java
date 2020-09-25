package de.swa.gmaf.plugin;

import java.io.File;
import java.net.URL;
import java.util.Vector;

import de.swa.mfv.FeatureVector;
import de.swa.mfv.Node;

public interface FeatureVectorPlugin {
	public void process(URL url, File f, byte[] bytes, FeatureVector fv);

	public boolean providesRecoursiveData();

	public boolean isGeneralPlugin();
	
	public Vector<Node> getDetectedNodes();
}
