package de.swa.mfv;

import java.util.UUID;

public class Context {
	private String name;
	private UUID id;
	
	public Context() {}
	public Context(UUID id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	
	public static Context getDefaultContext() {
		Context c = new Context(UUID.randomUUID(), "Default");
		return c;
	}
	
	
}
