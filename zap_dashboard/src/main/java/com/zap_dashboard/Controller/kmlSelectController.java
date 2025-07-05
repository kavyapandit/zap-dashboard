package com.zap_dashboard.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.zap_dashboard.entity.KmlCoordinates;
import com.zap_dashboard.Repository.KmlCoordinatesRepository;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class kmlSelectController {

    @Autowired
    private KmlCoordinatesRepository kmlCoordinatesRepository;

    @GetMapping("/api/kml-files")
    public List<KmlCoordinatesResponse> getKmlFilesByStateAndDistrict(
            @RequestParam(required = false) String state,
            @RequestParam(required = false) String district) {

        List<KmlCoordinates> kmlFiles;

        if (state != null && district != null) {
            kmlFiles = kmlCoordinatesRepository.findByStateAndDistrict(state, district);
        } else if (district != null) {
            kmlFiles = kmlCoordinatesRepository.findByDistrict(district);
        } else if (state != null) {
            kmlFiles = kmlCoordinatesRepository.findByState(state);
        } else {
            kmlFiles = kmlCoordinatesRepository.findAll();
        }

        return kmlFiles.stream()
            .map(kml -> new KmlCoordinatesResponse(kml.getId(), kml.getFileName(), kml.getState(), kml.getDistrict()))
            .collect(Collectors.toList());
    }

    @PostMapping("/api/kml-coordinates")
    public List<KmlCoordinatesDto> getSelectedKmlCoordinates(@RequestBody List<Long> kmlFileIds) {
        List<KmlCoordinates> selectedKmlFiles = kmlCoordinatesRepository.findAllById(kmlFileIds);

        return selectedKmlFiles.stream()
            .map(kml -> {
                String coordinates = kml.getCoordinates();
                String format = "standard"; // Default format
                
                // Detect coordinate format
                if (coordinates != null && coordinates.trim().startsWith("[[") && coordinates.trim().endsWith("]]")) {
                    format = "array";
                } else if (coordinates != null && 
                          (coordinates.trim().toLowerCase().contains("<placemark") || 
                           coordinates.trim().toLowerCase().contains("<polygon") || 
                           coordinates.trim().toLowerCase().contains("<linestring"))) {
                    format = "kml";
                }
                
                return new KmlCoordinatesDto(kml.getId(), kml.getFileName(), coordinates, format);
            })
            .collect(Collectors.toList());
    }

    // Response class for KML metadata
    public static class KmlCoordinatesResponse {
        private Long id;
        private String fileName;
        private String state;
        private String district;

        public KmlCoordinatesResponse(Long id, String fileName, String state, String district) {
            this.id = id;
            this.fileName = fileName;
            this.state = state;
            this.district = district;
        }

        public Long getId() { return id; }
        public String getFileName() { return fileName; }
        public String getState() { return state; }
        public String getDistrict() { return district; }
    }

    // Updated DTO to include format information
    public static class KmlCoordinatesDto {
        private Long id;
        private String fileName;
        private String coordinates;
        private String format; // "kml", "array", or "standard"

        public KmlCoordinatesDto(Long id, String fileName, String coordinates, String format) {
            this.id = id;
            this.fileName = fileName;
            this.coordinates = coordinates;
            this.format = format;
        }

        public Long getId() { return id; }
        public String getFileName() { return fileName; }
        public String getCoordinates() { return coordinates; }
        public String getFormat() { return format; }
    }
}