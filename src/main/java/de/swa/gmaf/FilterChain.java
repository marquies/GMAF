package de.swa.gmaf;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.Vector;

import javax.imageio.ImageIO;

import de.swa.gmaf.plugin.DocumentTextDetection;
import de.swa.gmaf.plugin.DominantColorDetection;
import de.swa.gmaf.plugin.ExifHandler;
import de.swa.gmaf.plugin.FaceDetection;
import de.swa.gmaf.plugin.FeatureVectorPlugin;
import de.swa.gmaf.plugin.LabelDetection;
import de.swa.gmaf.plugin.LandmarkDetection;
import de.swa.gmaf.plugin.LogoDetection;
import de.swa.gmaf.plugin.MoodDetection;
import de.swa.gmaf.plugin.ObjectLocalisation;
import de.swa.mfv.FeatureVector;
import de.swa.mfv.Location;
import de.swa.mfv.Node;
import de.swa.mfv.TechnicalAttribute;
import de.swa.mfv.builder.FeatureVectorBuilder;

public class FilterChain {
	private Vector<FeatureVectorPlugin> plugins = new Vector<FeatureVectorPlugin>();

	public FilterChain() {
		plugins.add(new ExifHandler());
		plugins.add(new DocumentTextDetection());
		plugins.add(new FaceDetection());
		plugins.add(new LabelDetection());
		plugins.add(new LandmarkDetection());
		plugins.add(new LogoDetection());
		plugins.add(new MoodDetection());
		plugins.add(new ObjectLocalisation());
		plugins.add(new DominantColorDetection());
	}
	
	public void process(URL url, File f, byte[] bytes, FeatureVector fv, int depth) {
		if (depth < 0) return;
		// process general data
		for (FeatureVectorPlugin fvp : plugins) {
			if (fvp.isGeneralPlugin()) fvp.process(url, f, bytes, fv);
		}
		
		try {
			Location loc = new Location(Location.TYPE_HIGHRES, url, url.toString());
			fv.addLocation(loc);
			loc = new Location(Location.TYPE_ORIGINAL, new URL("file:///" + f.getAbsolutePath()), f.getName());
			fv.addLocation(loc);
		}
		catch (Exception x) {}
		
		// process details
		for (FeatureVectorPlugin fvp : plugins) {
			if (!fvp.isGeneralPlugin()) {
				if (!fvp.providesRecoursiveData()) fvp.process(url, f, bytes, fv);
				else {
					fvp.process(url, f, bytes, fv);
					Vector<Node> nodes = fvp.getDetectedNodes();
					for (Node n : nodes) {
						fv.setCurrentNode(n);
						for (TechnicalAttribute ta : n.getTechnicalAttributes()) {
							try {
								if (ta.getWidth() == 0) continue;
								if (ta.getHeight() == 0) continue;
								
								// cut image
								BufferedImage img = ImageIO.read(f);
					            BufferedImage out = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);
					            BufferedImage section = out.getSubimage(ta.getRelative_x(), ta.getRelative_y(), ta.getWidth(), ta.getHeight());
								
					            ByteArrayOutputStream baos = new ByteArrayOutputStream();
					            ImageIO.write(section, "jpg", baos);
					            baos.flush();
					            byte[] sectionBytes = baos.toByteArray();
					            baos.close();
					            
//					            FileOutputStream fout = new FileOutputStream(new File("temp/section_" + System.currentTimeMillis()));
//					            ImageIO.write(section, "jpg", fout);
					            
								// reprocess parts of this image
								FeatureVector newImg = new ApiFacade().processImage(sectionBytes, "section.jpg", "tmp", depth --);
								FeatureVectorBuilder.mergeIntoFeatureVector(fv, newImg);
							}
							catch (Exception x) {
//								x.printStackTrace();
							}
						}
					}
				}
			}
		}
	}
}
