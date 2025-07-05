package com.zap_dashboard.entity;

import jakarta.persistence.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name="kml_tl")
public class kmltlEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="tlk_id")
	private Integer tlkId;
	
	@Column(name="state")
	private String state;
	
	@Column(name="tl_coordinates", columnDefinition = "TEXT")
	private String tlCoordinates;
	
	@Column(name="tl_sector_id")
	private String tlSectorid;
	
	@Column(name="location_name")
	private String locationName;
	
	@Column(name="annexure")
	private String annexure;

	public kmltlEntity() {}
	public kmltlEntity(Integer tlkId, String state, String tlCoordinates, String tlSectorid, String locationName,
			String annexure) {
		super();
		this.tlkId = tlkId;
		this.state = state;
		this.tlCoordinates = tlCoordinates;
		this.tlSectorid = tlSectorid;
		this.locationName = locationName;
		this.annexure = annexure;
	}

	public Integer getTlkId() {
		return tlkId;
	}

	public void setTlkId(Integer tlkId) {
		this.tlkId = tlkId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getTlCoordinates() {
		return tlCoordinates;
	}

	public void setTlCoordinates(String tlCoordinates) {
		this.tlCoordinates = tlCoordinates;
	}

	public String getTlSectorid() {
		return tlSectorid;
	}

	public void setTlSectorid(String tlSectorid) {
		this.tlSectorid = tlSectorid;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getAnnexure() {
		return annexure;
	}

	public void setAnnexure(String annexure) {
		this.annexure = annexure;
	}

	public String getCoordinates() {
	    return this.tlCoordinates;
	}

}
