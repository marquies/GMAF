package de.swa.mfv.builder;

import com.thoughtworks.xstream.XStream;

import de.swa.mfv.FeatureVector;

public class XMLEncodeDecode implements Flattener, Unflattener {
	public String flatten(FeatureVector fv) {	
		XStream xstream = new XStream();
		return xstream.toXML(fv);
		/*
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		XMLEncoder enc = new XMLEncoder(baos);
		enc.writeObject(fv);
		enc.close();
		return baos.toString();
		*/
	}
	
	public FeatureVector unflatten(String xml) {
		XStream xstream = new XStream();
		return (FeatureVector)xstream.fromXML(xml);
		/*
		XMLDecoder dec = new XMLDecoder(new ByteArrayInputStream(xml.getBytes()));
		FeatureVector fv = (FeatureVector)dec.readObject();
		return fv;
		*/
	}
}
