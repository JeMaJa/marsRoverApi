package com.yannick.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MarsPhoto {
	private Long id;
	private Integer sol;
	private MarsCamera camara;
	@JsonProperty("img_src") // this is to match the mapping
	private String imgSrc; // underscore in JSON becomes Camelcase in java 
	@JsonProperty("earth_date")
	private String earthDate;
	private MarsRover rover;
	
	public String getEarthDate() {
		return earthDate;
	}
	public void setEarthDate(String earthDate) {
		this.earthDate = earthDate;
	}
	public MarsRover getRover() {
		return rover;
	}
	public void setRover(MarsRover rover) {
		this.rover = rover;
	}
	
	@Override
	public String toString() {
		return "MarsPhoto [id=" + id + ", sol=" + sol + ", camara=" + camara + ", imgSrc=" + imgSrc + ", earthDate="
				+ earthDate + ", rover=" + rover + "]";
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getSol() {
		return sol;
	}
	public void setSol(Integer sol) {
		this.sol = sol;
	}
	public MarsCamera getCamara() {
		return camara;
	}
	public void setCamara(MarsCamera camara) {
		this.camara = camara;
	}
	public String getImgSrc() {
		return imgSrc;
	}
	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}

}
