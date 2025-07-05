package com.zap_dashboard.entity;

import jakarta.persistence.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name="kml_tsm")

public class kmltsmEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="tsk_id")
	private Integer tskId;
	
	@Column(name="state")
	private String state;
	
	@Column(name="district")
	private String district;
	
	@Column(name="tsm_sector_id")
	private String tsmSectorid;
	
	@Column(name="tsm_coordinates",columnDefinition = "TEXT")
	private String tsmCoordinates;
	
	@Column(name="status")
	private String status;
	
	public kmltsmEntity() {}

	public kmltsmEntity(Integer tskId, String state, String district, String tsmSectorid, String tsmCoordinates,
			String status) {
		super();
		this.tskId = tskId;
		this.state = state;
		this.district = district;
		this.tsmSectorid = tsmSectorid;
		this.tsmCoordinates = tsmCoordinates;
		this.status = status;
	}

	public Integer getTskId() {
		return tskId;
	}

	public void setTskId(Integer tskId) {
		this.tskId = tskId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getTsmSectorid() {
		return tsmSectorid;
	}

	public void setTsmSectorid(String tsmSectorid) {
		this.tsmSectorid = tsmSectorid;
	}

	public String getTsmCoordinates() {
		return tsmCoordinates;
	}

	public void setTsmCoordinates(String tsmCoordinates) {
		this.tsmCoordinates = tsmCoordinates;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCoordinates() {
		 return this.tsmCoordinates;
	}
}
