package com.zap_dashboard.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "kml_asm")
public class kmlasmEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ask_id") // Fixed the column name
    private Integer asmId;

    @Column(name = "state")
    private String state;

    @Column(name = "regions")
    private String regions;

    @Column(name = "asm_sector_id")
    private String asmSectorId;

    @Column(name = "asm_coordinates", columnDefinition = "TEXT") // For longer coordinate data
    private String asmCoordinates;

    @Column(name = "status")
    private String status;

    public kmlasmEntity() {}

    public kmlasmEntity(Integer asmId, String state, String regions, String asmSectorId, String asmCoordinates, String status) {
        this.asmId = asmId;
        this.state = state;
        this.regions = regions;
        this.asmSectorId = asmSectorId;
        this.asmCoordinates = asmCoordinates;
        this.status = status;
    }

    public Integer getAsmId() {
        return asmId;
    }

    public void setAsmId(Integer asmId) {
        this.asmId = asmId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRegions() {
        return regions;
    }

    public void setRegions(String regions) {
        this.regions = regions;
    }

    public String getAsmSectorId() {
        return asmSectorId;
    }

    public void setAsmSectorId(String asmSectorId) {
        this.asmSectorId = asmSectorId;
    }

    public String getAsmCoordinates() {
        return asmCoordinates;
    }

    public void setAsmCoordinates(String asmCoordinates) {
        this.asmCoordinates = asmCoordinates;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

	public String getCoordinates() {
		return this.asmCoordinates;
	}

	
	
}
