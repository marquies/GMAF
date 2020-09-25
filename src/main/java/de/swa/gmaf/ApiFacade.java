package de.swa.gmaf;

import java.io.File;
import java.net.URL;

import de.swa.gmaf.plugin.ExifHandler;
import de.swa.mfv.FeatureVector;
import de.swa.mfv.Node;
import de.swa.mfv.Security;

public class ApiFacade {
	public FeatureVector processImage(byte[] bytes, String assetName, String userId, int maxRecursions) {
		String suffix = ".jpg";
		if (assetName.indexOf(".") > 0) suffix = assetName.substring(assetName.lastIndexOf("."), assetName.length());

		URL location = TempURLProvider.provideTempUrl(bytes, suffix);
		File file = TempFileProvider.provideTempFile(bytes, suffix);
		System.out.println("Temp URL: " + location);
		System.out.println("Temp File: " + file.getAbsolutePath());
		
		FeatureVector fv = ExifHandler.getFeatureVectorFromExif(file);
		if (fv == null) {
			fv = new FeatureVector();
			Node n = new Node("Root-Image");
			fv.addNode(n);
			fv.setCurrentNode(n);
		}
		
		FilterChain fc = new FilterChain();
		fc.process(location, file, bytes, fv, maxRecursions);
		Security sec = new Security();
		sec.setAcl("private");
		sec.setOwner_id(userId);
		sec.setGroup_id("");
		return fv;
	}

	
}
