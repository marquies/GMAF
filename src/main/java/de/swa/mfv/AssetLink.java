package de.swa.mfv;

import java.net.URL;

public class AssetLink {
	public static final int TYPE_IMAGE = 0;
	public static final int TYPE_VIDEO = 1;
	public static final int TYPE_TEXT = 2;
	private int type;
	private URL location;
	private String assetName;
	
	public AssetLink() {}
	public AssetLink(int type, URL location, String assetName) {
		this.type = type;
		this.location = location;
		this.assetName = assetName;
	}
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public URL getLocation() {
		return location;
	}
	public void setLocation(URL location) {
		this.location = location;
	}
	public String getAssetName() {
		return assetName;
	}
	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}
}
