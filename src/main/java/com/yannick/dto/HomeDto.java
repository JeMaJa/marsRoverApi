package com.yannick.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.web.bind.annotation.RequestParam;

@Entity
@Table(name = "mars_api_preferences")
public class HomeDto {
/*
 * A DTO = Data Transfer Objects
 */
	
	private Long userId;
	private String marsApiRoverData;
	private Integer marsSol;
	
	private Boolean cameraFhaz;
	private Boolean cameraRhaz;
	private Boolean cameraMast;
	private Boolean cameraChemcam;
	private Boolean cameraMahli;
	private Boolean cameraMardi;
	private Boolean cameraNavcam;
	private Boolean cameraPancam;
	private Boolean cameraMinites;
	private Boolean rememberPreferences;
	
	
	
	public Boolean getRememberPreferences() {
		return rememberPreferences;
	}
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getUserId() {
		return userId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public void setRememberPreferences(Boolean rememberPreferences) {
		this.rememberPreferences = rememberPreferences;
	}
	@Column(length = 20)
	public String getMarsApiRoverData() {
		return marsApiRoverData;
	}
	
	public void setMarsApiRoverData(String marsApiRoverData) {
		this.marsApiRoverData = marsApiRoverData;
	}
	public Integer getMarsSol() {
		return marsSol;
	}
	public void setMarsSol(Integer marsSol) {
		this.marsSol = marsSol;
	}
	public Boolean cameraFhaz() {
		return cameraFhaz;
	}
	public void cameraFhaz(Boolean cameraFhaz) {
		this.cameraFhaz = cameraFhaz;
	}
	public Boolean cameraRhaz() {
		return cameraRhaz;
	}
	public void setcameraRhaz(Boolean cameraRhaz) {
		this.cameraRhaz = cameraRhaz;
	}
 
	public void setcameraMast(Boolean cameraMast) {
		this.cameraMast = cameraMast;
	}
	public Boolean getCameraChemcam() {
		return cameraChemcam;
	}
	public void setcameraChemcam(Boolean cameraChemcam) {
		this.cameraChemcam = cameraChemcam;
	}
	
	public Boolean getCameraFhaz() {
		return cameraFhaz;
	}
	public void setCameraFhaz(Boolean cameraFhaz) {
		this.cameraFhaz = cameraFhaz;
	}
	public Boolean getCameraRhaz() {
		return cameraRhaz;
	}
	public void setCameraRhaz(Boolean cameraRhaz) {
		this.cameraRhaz = cameraRhaz;
	}
	public Boolean getCameraMast() {
		return cameraMast;
	}
	public void setCameraMast(Boolean cameraMast) {
		this.cameraMast = cameraMast;
	}
	public Boolean getCameraMahli() {
		return cameraMahli;
	}
	public void setCameraMahli(Boolean cameraMahli) {
		this.cameraMahli = cameraMahli;
	}
	public Boolean getCameraMardi() {
		return cameraMardi;
	}
	public void setCameraMardi(Boolean cameraMardi) {
		this.cameraMardi = cameraMardi;
	}
	public Boolean getCameraNavcam() {
		return cameraNavcam;
	}
	public void setCameraNavcam(Boolean cameraNavcam) {
		this.cameraNavcam = cameraNavcam;
	}
	public Boolean getCameraPancam() {
		return cameraPancam;
	}
	public void setCameraPancam(Boolean cameraPancam) {
		this.cameraPancam = cameraPancam;
	}
	public Boolean getCameraMinites() {
		return cameraMinites;
	}
	public void setCameraMinites(Boolean cameraMinites) {
		this.cameraMinites = cameraMinites;
	}
	public void setCameraChemcam(Boolean cameraChemcam) {
		this.cameraChemcam = cameraChemcam;
	}
	public HomeDto() {
		super();
		this.cameraChemcam = false;
		this.cameraFhaz = true;
		this.cameraMahli = false;
		this.cameraMardi = false;
		this.cameraMast = false;
		this.cameraMinites = false;
		this.cameraNavcam = false;
		this.cameraPancam = false;
		this.cameraRhaz = false;
		//this.marsSol = 10;
		//this.marsApiRoverData = "Curiosity";
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "HomeDto [userId=" + userId + ", marsApiRoverData=" + marsApiRoverData + ", marsSol=" + marsSol + "]";
	}


}

