package com.zap_dashboard.entity;

public class FormData {
    private String formType;
    private String state;
    private String district;
    private String sectorId;
    private String location;
    private String requestedBy;
    private String file; // For file upload (if needed)

    // Getters and setters
    public String getFormType() {
        return formType;
    }

    public void setFormType(String formType) {
        this.formType = formType;
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

    public String getSectorId() {
        return sectorId;
    }

    public void setSectorId(String sectorId) {
        this.sectorId = sectorId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRequestedBy() {
        return requestedBy;
    }

    public void setRequestedBy(String requestedBy) {
        this.requestedBy = requestedBy;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    // Convert FormData to kmldistributorEntity
    public kmldistributorEntity toKmlDistributor() 
    {
        return new kmldistributorEntity(null, this.state,this.district,
            null, // districtCoordinates (can be updated later)
            this.sectorId,
            this.location,
            this.requestedBy,
            null, // zone (can be updated later)
            "active" // status
        );
    }
}