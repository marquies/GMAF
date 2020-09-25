package de.swa.mfv;

import java.util.Date;
import java.util.UUID;

public class GeneralMetadata {
	private UUID id;
	private long longitude;
	private long latitude;
	private String cameraModel;
	private String lensModel;
	private float aperture;
	private float exposure;
	private String cityNearBy;
	private long focalLength;
	private int resolution;
	private int width;
	private int height;
	private Date date;
	private long fileSize;
	private int iso;
	private long shutterSpeed;
	
	public GeneralMetadata() {}
	public GeneralMetadata(UUID id, long longitude, long latitude, String camera, String lens, float aperture, float exposure, long focalLength, int resolution, int width, int height, Date date, long fileSize, int iso, long shutterSpeed) {
		this.id = id;
		this.longitude = longitude;
		this.latitude = latitude;
		this.cameraModel = camera;
		this.lensModel = lens;
		this.aperture = aperture;
		this.exposure = exposure;
		this.focalLength = focalLength;
		this.resolution = resolution;
		this.width = width;
		this.height = height;
		this.date = date;
		this.fileSize = fileSize;
		this.iso = iso;
		this.shutterSpeed = shutterSpeed;
	}
	
	public UUID getId() {
		if (id == null) id = UUID.randomUUID();
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public long getLongitude() {
		return longitude;
	}
	public void setLongitude(long longitude) {
		this.longitude = longitude;
	}
	public long getLatitude() {
		return latitude;
	}
	public void setLatitude(long latitude) {
		this.latitude = latitude;
	}
	public String getCameraModel() {
		return cameraModel;
	}
	public void setCameraModel(String cameraModel) {
		this.cameraModel = cameraModel;
	}
	public String getLensModel() {
		return lensModel;
	}
	public void setLensModel(String lensModel) {
		this.lensModel = lensModel;
	}
	public float getAperture() {
		return aperture;
	}
	public void setAperture(float aperture) {
		this.aperture = aperture;
	}
	public float getExposure() {
		return exposure;
	}
	public void setExposure(float exposure) {
		this.exposure = exposure;
	}
	public String getCityNearBy() {
		return cityNearBy;
	}
	public void setCityNearBy(String cityNearBy) {
		this.cityNearBy = cityNearBy;
	}
	public long getFocalLength() {
		return focalLength;
	}
	public void setFocalLength(long focalLength) {
		this.focalLength = focalLength;
	}
	public int getResolution() {
		return resolution;
	}
	public void setResolution(int resolution) {
		this.resolution = resolution;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	public int getIso() {
		return iso;
	}
	public void setIso(int iso) {
		this.iso = iso;
	}
	public long getShutterSpeed() {
		return shutterSpeed;
	}
	public void setShutterSpeed(long shutterSpeed) {
		this.shutterSpeed = shutterSpeed;
	}
	
	
}
