package de.swa.gmaf;

import java.io.FileInputStream;
import java.io.RandomAccessFile;

import de.swa.mfv.FeatureVector;
import de.swa.mfv.builder.FeatureVectorBuilder;
import de.swa.mfv.builder.GraphMLFlattener;
import de.swa.mfv.builder.ImageFlattener;
import de.swa.mfv.builder.JsonFlattener;
import de.swa.mfv.builder.XMLEncodeDecode;

public class Test {
	//  NOTE: add envorionment entry: GOOGLE_APPLICATION_CREDENTIALS -> /Users/stefan_wagenpfeil/eclipse-workspace/gmaf/API_Keys/Diss-0e43fbd15daa.json
	public static void main(String[] args) throws Exception {
		RandomAccessFile rf = new RandomAccessFile("graph.xml", "r");
		String line = "";
		String content = "";
		while ((line = rf.readLine()) != null) {
			content += line;
		}
		rf.close();
		String s = "";
		
		FeatureVector fv = FeatureVectorBuilder.unflatten(content, new XMLEncodeDecode());
//		System.out.println(FeatureVectorBuilder.flatten(fv, new JsonFlattener()));
//		String path = FeatureVectorBuilder.flatten(fv, new ImageFlattener());
//		Runtime.getRuntime().exec("open " + path);
//
//		if (true) return;
		ApiFacade af = new ApiFacade();
		
//		String testFile = "Strand-Gardasee.jpg";
//		FileInputStream fs = new FileInputStream("/Users/stefan_wagenpfeil/Desktop/DSC_8961_2.jpg");
//		byte[] bytes = fs.readAllBytes();
//		fv = af.processImage(bytes, testFile, "sw", 2);
//		
//		s = FeatureVectorBuilder.flatten(fv, new GraphMLFlattener());
		
//		rf = new RandomAccessFile("graph.graphml", "rw");
//		rf.setLength(0);
//		rf.writeBytes(s);
//		rf.close();
//		
		s = FeatureVectorBuilder.flatten(fv,  new JsonFlattener());
		rf = new RandomAccessFile("graph.json", "rw");
		rf.setLength(0);
		rf.writeBytes(s);
		rf.close();
		
		s = FeatureVectorBuilder.flatten(fv, new XMLEncodeDecode());
		rf = new RandomAccessFile("graph.xml", "rw");
		rf.setLength(0);
		rf.writeBytes(s);
		rf.close();
	}
}
