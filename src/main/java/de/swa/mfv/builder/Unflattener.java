package de.swa.mfv.builder;

import de.swa.mfv.FeatureVector;

public interface Unflattener {
	public FeatureVector unflatten(String s);
}
