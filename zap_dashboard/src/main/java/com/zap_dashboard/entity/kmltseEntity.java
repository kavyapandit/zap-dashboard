package com.zap_dashboard.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "kml_tse")
public class kmltseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generates the ID
    @Column(name = "tk_id")
    private Integer tkId;

    @Column(name = "state")
    private String state;

    @Column(name = "tse_coordinates",columnDefinition = "TEXT")
    private String tseCoordinates;

    @Column(name = "tse_sector_id")
    private String tseSectorid;

    @Column(name = "location_name")
    private String locationName;

    @Column(name = "tier")
    private String tier;

    public kmltseEntity() {}

    public kmltseEntity(String state, String tseCoordinates, String tseSectorid, String locationName, String tier) {
        this.state = state;
        this.tseCoordinates = tseCoordinates;
        this.tseSectorid = tseSectorid;
        this.locationName = locationName;
        this.tier = tier;
    }

    public Integer getTkId() {
        return tkId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTseCoordinates() {
        return tseCoordinates;
    }

    public void setTseCoordinates(String tseCoordinates) {
        this.tseCoordinates = tseCoordinates;
    }

    public String getTseSectorid() {
        return tseSectorid;
    }

    public void setTseSectorid(String tseSectorid) {
        this.tseSectorid = tseSectorid;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getTier() {
        return tier;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }

	public String getCoordinates() {
		 return this.tseCoordinates;
	}
}