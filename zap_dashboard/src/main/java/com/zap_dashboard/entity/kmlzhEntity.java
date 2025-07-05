package com.zap_dashboard.entity;
import jakarta.persistence.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name="kml_zh")
public class kmlzhEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="zhk_id")
	private Integer zhkId;
	
	@Column(name="zone")
	private String zone;
	
	@Column(name="zh_sector_id")
	private String zhSectorid;
	
	@Column(name="zh_coordinates",columnDefinition = "TEXT")
	private String zhCoordinates;
	
	@Column(name="status")
	private String status;

	public kmlzhEntity()
	{}
	
	public kmlzhEntity(Integer zhkId, String zone, String zhSectorid, String zhCoordinates, String status) {
		super();
		this.zhkId = zhkId;
		this.zone = zone;
		this.zhSectorid = zhSectorid;
		this.zhCoordinates = zhCoordinates;
		this.status = status;
	}

	public Integer getZhkId() {
		return zhkId;
	}

	public void setZhkId(Integer zhkId) {
		this.zhkId = zhkId;
	}

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	public String getZhSectorid() {
		return zhSectorid;
	}

	public void setZhSectorid(String zhSectorid) {
		this.zhSectorid = zhSectorid;
	}

	public String getZhCoordinates() {
		return zhCoordinates;
	}

	public void setZhCoordinates(String zhCoordinates) {
		this.zhCoordinates = zhCoordinates;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCoordinates() {
		 return this.zhCoordinates;
	}

}
