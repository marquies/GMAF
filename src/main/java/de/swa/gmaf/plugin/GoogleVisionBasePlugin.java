package de.swa.gmaf.plugin;

import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.imageio.ImageIO;

import com.google.cloud.vision.v1.AnnotateImageRequest;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.BatchAnnotateImagesResponse;
import com.google.cloud.vision.v1.BoundingPoly;
import com.google.cloud.vision.v1.Feature;
import com.google.cloud.vision.v1.Image;
import com.google.cloud.vision.v1.ImageAnnotatorClient;
import com.google.cloud.vision.v1.NormalizedVertex;
import com.google.cloud.vision.v1.Vertex;
import com.google.protobuf.ByteString;

import de.swa.mfv.FeatureVector;
import de.swa.mfv.Node;
import de.swa.mfv.TechnicalAttribute;

public abstract class GoogleVisionBasePlugin implements FeatureVectorPlugin {
	public Vector<Node> getDetectedNodes() {
		return null;
	}

	public boolean isGeneralPlugin() {
		return false;
	}
	
	private int width, height;

	public final void process(URL url, File f, byte[] bytes, FeatureVector fv) {
		try {
			BufferedImage img = ImageIO.read(f);
			width = img.getWidth(null);
			height = img.getHeight(null);
		}
		catch (Exception x) {
			x.printStackTrace();
		}
		try {
			ImageAnnotatorClient vision = ImageAnnotatorClient.create();
			ByteString imgBytes = ByteString.copyFrom(bytes);
			List<AnnotateImageRequest> requests = new ArrayList<AnnotateImageRequest>();
			Image img = Image.newBuilder().setContent(imgBytes).build();

			Feature feat = getSearchFeature(); // Feature.newBuilder().setType(Type.LABEL_DETECTION).build();

			AnnotateImageRequest request = AnnotateImageRequest.newBuilder().addFeatures(feat).setImage(img).build();
			requests.add(request);
			BatchAnnotateImagesResponse response = vision.batchAnnotateImages(requests);
			List<AnnotateImageResponse> responses = response.getResponsesList();

			for (AnnotateImageResponse res : responses) {
				if (res.hasError()) {
					System.out.format("Error: %s%n", res.getError().getMessage());
					return;
				}
				try {
					processResult(res, fv);
				} catch (Exception x) {
					System.out.println("ERROR processing annotation " + res.toString() + " > " + x.getMessage());
				}
			}
			vision.close();
		} catch (Exception x) {
			x.printStackTrace();
			System.out.println("Google Error: " + x);
		}
	}

	protected TechnicalAttribute getBoundingBox(BoundingPoly poly) {
		int x = Integer.MAX_VALUE;
		int y = Integer.MAX_VALUE;
		int max_x = 0;
		int max_y = 0;
		if (poly.getNormalizedVerticesCount() > 0) {
			for (NormalizedVertex v : poly.getNormalizedVerticesList()) {
				int vx = (int)(v.getX() * width);
				int vy = (int)(v.getY() * height);
				if (vx < x)
					x = vx;
				if (vx > max_x)
					max_x = vx;
				if (vy < y)
					y = vy;
				if (vy > max_y)
					max_y = vy;
			}
		} else {
			for (Vertex v : poly.getVerticesList()) {
				if (v.getX() < x)
					x = v.getX();
				if (v.getX() > max_x)
					max_x = v.getX();
				if (v.getY() < y)
					y = v.getY();
				if (v.getY() > max_y)
					max_y = v.getY();
			}
		}
		if (x == Integer.MAX_VALUE)
			x = 0;
		if (y == Integer.MAX_VALUE)
			y = 0;
		TechnicalAttribute ta = new TechnicalAttribute(x, y, (max_x - x), (max_y - y), 0, 0);
		return ta;
	}

	protected abstract Feature getSearchFeature();

	protected abstract void processResult(AnnotateImageResponse res, FeatureVector fv);

	public abstract boolean providesRecoursiveData();
}
