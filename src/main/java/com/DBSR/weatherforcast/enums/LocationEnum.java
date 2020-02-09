package com.DBSR.weatherforcast.enums;

public enum LocationEnum {
	
	CA("Campbell, CA", 37.2872,-121.9500),
	OM("Omaha, NE", 41.2565,-95.9345),
	AU("Austin, TX", 30.2672,-97.7431),
	JP("Niseko, Japan", 42.8048,42.8048),
	NR("Nara, Japan",34.6851,135.8048),
	JK("Jakarta, Indonesia", 6.2088,106.8456);
	
	private String locationCode;
	private Double lat;
	private Double lan;
	
	private LocationEnum(String locationCode, Double lat, Double lan) {
		this.locationCode = locationCode;
		this.lat = lat;
		this.lan = lan;
	}

	public String getLocationCode() {
		return locationCode;
	}

	public Double getLat() {
		return lat;
	}

	public Double getLan() {
		return lan;
	}
	
	

}
