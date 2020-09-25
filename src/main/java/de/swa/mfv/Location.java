package de.swa.mfv;

import java.net.URL;

public class Location {
	public static int TYPE_ORIGINAL;
	public static int TYPE_ORIGINAL_COPY;
	public static int TYPE_HIGHRES;
	public static int TYPE_LOWRES;
	public static int TYPE_THUMBNAIL;
	
	private int type;
	private URL location;
	private String name;
	
	public Location() {}
	public Location(int type, URL location, String name) {
		this.type = type;
		this.location = location;
		this.name = name;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
