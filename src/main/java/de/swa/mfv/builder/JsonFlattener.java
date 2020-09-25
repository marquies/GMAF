package de.swa.mfv.builder;

import com.google.gson.Gson;

import de.swa.mfv.FeatureVector;

public class JsonFlattener implements Flattener {
	public String flatten(FeatureVector fv) {
		Gson gson = new Gson();
		String json = gson.toJson(fv);
		return json;
	}
}
