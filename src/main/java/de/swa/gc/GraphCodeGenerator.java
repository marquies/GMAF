package de.swa.gc;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.UUID;
import java.util.Vector;

import javax.imageio.ImageIO;

import de.swa.mfv.CompositionRelationship;
import de.swa.mfv.Context;
import de.swa.mfv.FeatureVector;
import de.swa.mfv.Node;
import de.swa.mfv.TechnicalAttribute;
import de.swa.mfv.Weight;
import de.swa.mfv.builder.Flattener;

public class GraphCodeGenerator implements Flattener {
	public String flatten(FeatureVector fv) {
		Vector<Node> axis = FeatureVector.allNodes;
		
		int size = axis.size() + Context.allContexts.size();
		System.out.println("Axis-Size: " + size);
		BufferedImage img = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
		System.out.println("created graph code image with " + size + " x " + size + " pixels");
		
		long start = System.currentTimeMillis();
		
		for (int i = 0; i < axis.size(); i++) {
			for (int j = 0; j < axis.size(); j++) {
				Color c = Color.white;
				img.setRGB(i, j, c.getRGB());
			}
		}
		
		for (int i = 0; i < axis.size(); i++) {
			Node n = axis.get(i);
			String key = n.getName();
			System.out.println("AXIS " + i + " -> " + key);
			
			// Nodes colored in black, rating is applied as well
			Color c = Color.black;
			img.setRGB(i, i, c.getRGB());
			if (n.getChildNodes().size() > 0) c = Color.DARK_GRAY;

			// Child Link
			for (Node child : n.getChildNodes()) {
				int j = axis.indexOf(child);
				
				c = Color.blue;
				img.setRGB(i, j, c.getRGB());
			}
			
			// weight + context
			for (Weight w : n.getWeights()) {
				Context ctx = w.getContext();
				int j = axis.size() + Context.allContexts.indexOf(ctx);
				int g = 255;
				try {
					g = (int)(255 * w.getWeight());
					g = Math.max(255, g);
				}
				catch (Exception x) {}
				c = new Color(0, g, 0);
				img.setRGB(i, j, c.getRGB());
			}
			
			for (CompositionRelationship cr : n.getCompositionRelationships()) {
				Node nj = cr.getRelatedObject();
				int j = axis.indexOf(nj);
				
				if (cr.getType() == CompositionRelationship.RELATION_ABOVE) c = new Color(20, 255, 20);
				if (cr.getType() == CompositionRelationship.RELATION_ATTACHED_TO) c = new Color(40, 255, 40);
				if (cr.getType() == CompositionRelationship.RELATION_BEFORE) c = new Color(60, 255, 60);
				if (cr.getType() == CompositionRelationship.RELATION_BEHIND) c = new Color(80, 255, 80);
				if (cr.getType() == CompositionRelationship.RELATION_NEXT_TO) c = new Color(100, 255, 100);
				if (cr.getType() == CompositionRelationship.RELATION_PART_OF) c = new Color(120, 255, 120);
				if (cr.getType() == CompositionRelationship.RELATION_UNDER) c = new Color(140, 255, 140);
				img.setRGB(i, j, c.getRGB());
			}
		}
		
		long end = System.currentTimeMillis();
		
		UUID fileName = UUID.randomUUID();
		File outputFile = new File(fileName + ".bmp");
		try {
			ImageIO.write(img, "bmp", outputFile);
		}
		catch (Exception x) {
			x.printStackTrace();
		}
		System.out.println("graphcode written in " + (end - start) + " milliseconds");
		return fileName + ".bmp";
	}
}
