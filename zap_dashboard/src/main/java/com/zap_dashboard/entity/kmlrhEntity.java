package com.zap_dashboard.entity;

import jakarta.persistence.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name="kml_rh")
public class kmlrhEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="rhk_id")
	private Integer rhkId;
	
	@Column(name="state")
	private String state;
	
	@Column(name="rh_sector_id")
	private String rhSectorid;
	
	@Column(name="rh_coordinates" ,columnDefinition = "TEXT")
	private String rhCoordinates;
	
	@Column(name="status")
	private String status;

	public kmlrhEntity() {}
	public kmlrhEntity(Integer rhkId, String state, String rhSectorid, String rhCoordinates, String status) {
		super();
		this.rhkId = rhkId;
		this.state = state;
		this.rhSectorid = rhSectorid;
		this.rhCoordinates = rhCoordinates;
		this.status = status;
	}

	public Integer getRhkId() {
		return rhkId;
	}

	public void setRhkId(Integer rhkId) {
		this.rhkId = rhkId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getRhSectorid() {
		return rhSectorid;
	}

	public void setRhSectorid(String rhSectorid) {
		this.rhSectorid = rhSectorid;
	}

	public String getRhCoordinates() {
		return rhCoordinates;
	}

	public void setRhCoordinates(String rhCoordinates) {
		this.rhCoordinates = rhCoordinates;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	public String getCoordinates() {
		 return this.rhCoordinates;
	}
}
