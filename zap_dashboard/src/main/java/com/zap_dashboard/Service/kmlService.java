package com.zap_dashboard.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zap_dashboard.Repository.*;
import com.zap_dashboard.entity.*;

import java.util.List;

@Service
public class kmlService {

    @Autowired
    private kmlzhRepository zhRepository;

    @Autowired
    private kmlrhRepository rhRepository;

    @Autowired
    private kmlasmRepository asmRepository;

    @Autowired
    private kmltlRepository tlRepository;

    @Autowired
    private kmltsmRepository tsmRepository;

    @Autowired
    private kmltseRepository tseRepository;

    @Autowired
    private kmldistributorRepository distributorRepository;

    // Methods to fetch KML data for different user types
    public String getKMLDataForZh(String sectorId) {
        List<kmlzhEntity> zhLocations = zhRepository.findBySectorId(sectorId);
        return generateKml(zhLocations);
    }

    public String getKMLDataForRh(String sectorId) {
        List<kmlrhEntity> rhLocations = rhRepository.findBySectorId(sectorId);
        return generateKml(rhLocations);
    }


    public String getKMLDataForAsm(String sectorId) {
        List<kmlasmEntity> asmLocations = asmRepository.findBySectorId(sectorId);
        return generateKml(asmLocations);
    }

    public String getKMLDataForTsm(String sectorId) {
        List<kmltsmEntity> tsmLocations = tsmRepository.findBySectorId(sectorId);
        return generateKml(tsmLocations);
    }

    public String getKMLDataForTl(String sectorId) {
        List<kmltlEntity> tlLocations = tlRepository.findBySectorId(sectorId);
        return generateKml(tlLocations);
    }

    public String getKMLDataForDistributor(String sectorId) {
        List<kmldistributorEntity> distributorLocations = distributorRepository.findBySectorId(sectorId);
        return generateKml(distributorLocations);
    }

    public String getKMLDataForTse(String sectorId) {
        List<kmltseEntity> tseLocations = tseRepository.findBySectorId(sectorId);
        return generateKml(tseLocations);
    }

    // Method to generate KML from stored coordinate strings
    private String generateKml(List<?> locations) {
        StringBuilder kml = new StringBuilder();
        kml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>")
           .append("<kml xmlns=\"http://www.opengis.net/kml/2.2\">")
           .append("<Document>");

        for (Object location : locations) {
            String coordinates = getCoordinates(location);
            if (coordinates != null && !coordinates.isEmpty()) {
                String[] coordinateLines = coordinates.split("\\s+"); // Split lines

                for (String line : coordinateLines) {
                    String[] parts = line.split(",");
                    if (parts.length >= 2) { // Ensure valid coordinate format
                        String longitude = parts[0].trim();
                        String latitude = parts[1].trim();

                        kml.append("<Placemark>")
                           .append("<Point>")
                           .append("<coordinates>").append(longitude).append(",").append(latitude).append("</coordinates>")
                           .append("</Point>")
                           .append("</Placemark>");
                    }
                }
            }
        }

        kml.append("</Document>")
           .append("</kml>");

        return kml.toString();
    }

    // Extract coordinate field from entity
    private String getCoordinates(Object location) {
        if (location instanceof kmlzhEntity) {
            return ((kmlzhEntity) location).getCoordinates();
        } else if (location instanceof kmlrhEntity) {
            return ((kmlrhEntity) location).getCoordinates();
        } else if (location instanceof kmlasmEntity) {
            return ((kmlasmEntity) location).getCoordinates();
        } else if (location instanceof kmltlEntity) {
            return ((kmltlEntity) location).getCoordinates();
        } else if (location instanceof kmltseEntity) {
            return ((kmltseEntity) location).getCoordinates();
        } else if (location instanceof kmldistributorEntity) {
            return ((kmldistributorEntity) location).getCoordinates();
        } else if (location instanceof kmltsmEntity) {
            return ((kmltsmEntity) location).getCoordinates();
        }
        return null;
    }

    // Method to fetch all sector IDs from all repositories
    public List<String> findAllSectorIds() {
        List<String> sectorIds = zhRepository.findAllSectorIds();
        sectorIds.addAll(rhRepository.findAllSectorIds());
        sectorIds.addAll(asmRepository.findAllSectorIds());
        sectorIds.addAll(tsmRepository.findAllSectorIds());
        sectorIds.addAll(tlRepository.findAllSectorIds());
        sectorIds.addAll(tseRepository.findAllSectorIds());
        sectorIds.addAll(distributorRepository.findAllSectorIds());
        return sectorIds;
    }

	public List<String> findAllSectorIdsForZh() {
		return zhRepository.findAllSectorIds();
	}

	public List<String> findAllSectorIdsForRh() {
		return rhRepository.findAllSectorIds();
	}

	public List<String> findAllSectorIdsForAsm() {
		return asmRepository.findAllSectorIds();
	}

	public List<String> findAllSectorIdsForTsm() {
		return tsmRepository.findAllSectorIds();
	}

	public List<String> findAllSectorIdsForDistributor() {
		return distributorRepository.findAllSectorIds();
	}

	public List<String> findAllSectorIdsForTl() {
		return tlRepository.findAllSectorIds();
	}

	public List<String> findAllSectorIdsForTse() {
		return tseRepository.findAllSectorIds();
	}
	
}
