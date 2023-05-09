package com.model;

import java.io.Serializable;

public class Address implements Serializable{
	private String buildingName;
	private int flatNo;
	private String streetName, city;

	public Address(String buildingName, int flatNo, String streetName, String city) {
		super();
		this.buildingName = buildingName;
		this.flatNo = flatNo;
		this.streetName = streetName;
		this.city = city;
	}
	
	public String getBuildingName() {
		return buildingName;
	}


	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}


	public int getFlatNo() {
		return flatNo;
	}


	public void setFlatNo(int flatNo) {
		this.flatNo = flatNo;
	}


	public String getStreetName() {
		return streetName;
	}


	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}



	@Override
	public String toString() {
		return "Address [buildingName=" + buildingName + ", flatNo=" + flatNo + ", streetName=" + streetName + ", city="
				+ city + "]";
	}
	

}
