package com.zap_dashboard.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@RequestMapping("/api/kml")
@CrossOrigin(origins = "http://localhost:3000")
public class kmlFormController {

    @Autowired
    private com.zap_dashboard.Service.kmlFormService kmlService;

    // ✅ Upload and process KML file
    @PostMapping("/upload")
    public ResponseEntity<String> uploadKml(
            @RequestParam("formType") String formType,
            @RequestParam("location") String location,
            @RequestParam("state") String state,
            @RequestParam("sectorId") String sectorId,
            @RequestParam("file") MultipartFile file,
            @RequestParam(required = false) String district,
            @RequestParam(required = false) String requestedBy
    ) {
        try {
            String result = kmlService.processKmlFile(formType, file, state, sectorId, location, requestedBy, district, null);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    // ✅ Fetch KML coordinates for a specific sectorId
    @GetMapping("/{sectorId}")
    public ResponseEntity<String> getKmlData(@PathVariable String sectorId) {
        Optional<String> coordinates = kmlService.getCoordinatesBySectorId(sectorId);
        
        return coordinates.map(ResponseEntity::ok)
                          .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
