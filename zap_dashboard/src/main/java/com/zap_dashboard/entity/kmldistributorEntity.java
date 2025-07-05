package com.zap_dashboard.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "kml_distributor") // Correct table name
public class kmldistributorEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dk_id") // Correct column name
    private Integer dkId; // Field name corrected to match Java conventions
    
    @Column(name = "state", length = 50) // Length matches varchar(50)
    private String state;
    
    @Column(name = "district_code", length = 10) // Length matches varchar(10)
    private String districtCode;
    
    @Column(name = "distributor_coordinates", columnDefinition = "LONGTEXT") // Adjusted for longtext
    private String distributorCoordinates;
    
    @Column(name = "distributor_sector_id", length = 10) // Length matches varchar(10)
    private String distributorSectorId;
    
    @Column(name = "location_name", length = 100) // Length matches varchar(100)
    private String locationName;
    
    @Column(name = "requested_by", length = 255) // Length matches varchar(255)
    private String requestedBy;
    
    @Column(name = "zone", length = 20) // Length matches varchar(20)
    private String zone;
    
    @Column(name = "status", length = 50) // Length matches varchar(50)
    private String status;

    // Default constructor
    public kmldistributorEntity() {}

    // Constructor with all fields
    public kmldistributorEntity(Integer dkId, String state, String districtCode, String distributorCoordinates,
                                String distributorSectorId, String locationName, String requestedBy, String zone, String status) {
        this.dkId = dkId;
        this.state = state;
        this.districtCode = districtCode;
        this.distributorCoordinates = distributorCoordinates;
        this.distributorSectorId = distributorSectorId;
        this.locationName = locationName;
        this.requestedBy = requestedBy;
        this.zone = zone;
        this.status = status;
    }

    // Getters and Setters

    public Integer getDkId() {
        return dkId;
    }

    public void setDkId(Integer dkId) {
        this.dkId = dkId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getDistributorCoordinates() {
        return distributorCoordinates;
    }

    public void setDistributorCoordinates(String distributorCoordinates) {
        this.distributorCoordinates = distributorCoordinates;
    }

    public String getDistributorSectorId() {
        return distributorSectorId;
    }

    public void setDistributorSectorId(String distributorSectorId) {
        this.distributorSectorId = distributorSectorId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getRequestedBy() {
        return requestedBy;
    }

    public void setRequestedBy(String requestedBy) {
        this.requestedBy = requestedBy;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

	public String getCoordinates() {
		 return this.distributorCoordinates;
	}
}
