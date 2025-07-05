package com.zap_dashboard.Controller;

import com.zap_dashboard.Service.kmlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/kml")
@CrossOrigin(origins = "http://localhost:3000")
public class kmlController {

    @Autowired
    private kmlService kmlService;

    // ✅ Endpoint to view KML data for each user type (Zh, Rh, Asm, etc.)
    @GetMapping("/view/{userType}/{sectorId}")
    public ResponseEntity<String> getKML(@PathVariable String userType, @PathVariable String sectorId) {
        try {
            String kmlData = null;

            // Fetch KML data based on the user type
            switch (userType.toLowerCase()) {
                case "zh":
                    kmlData = kmlService.getKMLDataForZh(sectorId);
                    break;
                case "rh":
                    kmlData = kmlService.getKMLDataForRh(sectorId); // Pass sectorId properly
                    break;
                case "asm":
                    kmlData = kmlService.getKMLDataForAsm(sectorId);
                    break;
                case "tsm":
                    kmlData = kmlService.getKMLDataForTsm(sectorId);
                    break;
                case "tl":
                    kmlData = kmlService.getKMLDataForTl(sectorId);
                    break;
                case "distributor":
                    kmlData = kmlService.getKMLDataForDistributor(sectorId);
                    break;
                case "tse":
                    kmlData = kmlService.getKMLDataForTse(sectorId);
                    break;
                default:
                    return ResponseEntity.badRequest().body("Invalid user type: " + userType);
            }

            // Check if KML data was found
            if (kmlData == null || kmlData.isEmpty()) {
                return ResponseEntity.status(404).body("KML data not found for user type: " + userType);
            }

            // Return the KML data if found
            return ResponseEntity.ok(kmlData);

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error fetching KML data: " + e.getMessage());
        }
    }


    // ✅ Fetch all user types (Zh, Rh, Asm, etc.)
    @GetMapping("/user-types")
    public ResponseEntity<List<String>> getUserTypes() {
        try {
            // Return a list of valid user types (Zh, Rh, Asm, etc.)
            List<String> userTypes = List.of("zh", "rh", "asm", "tsm", "tl", "distributor", "tse");

            return ResponseEntity.ok(userTypes);

        } catch (Exception e) {
            // Handle errors and return a 500 error
            return ResponseEntity.status(500).body(null);
        }
    }

    // ✅ Fetch sector IDs for a specific user type
    @GetMapping("/sector-ids/{userType}")
    public ResponseEntity<List<String>> getSectorIdsByUserType(@PathVariable String userType) {
        try {
            List<String> sectorIds = null;

            // Fetch sector IDs based on the user type
            switch (userType.toLowerCase()) {
                case "zh":
                    sectorIds = kmlService.findAllSectorIdsForZh();
                    break;
                case "rh":
                    sectorIds = kmlService.findAllSectorIdsForRh();
                    break;
                case "asm":
                    sectorIds = kmlService.findAllSectorIdsForAsm();
                    break;
                case "tsm":
                    sectorIds = kmlService.findAllSectorIdsForTsm();
                    break;
                case "tl":
                    sectorIds = kmlService.findAllSectorIdsForTl();
                    break;
                case "distributor":
                    sectorIds = kmlService.findAllSectorIdsForDistributor();
                    break;
                case "tse":
                    sectorIds = kmlService.findAllSectorIdsForTse();
                    break;
                default:
                    return ResponseEntity.badRequest().body(null);
            }

            // Return sector IDs if found
            if (sectorIds != null && !sectorIds.isEmpty()) {
                return ResponseEntity.ok(sectorIds);
            } else {
                return ResponseEntity.status(404).body(null);
            }

        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    // ✅ Fetch all sector IDs (from all repositories)
    @GetMapping("/sector-ids")
    public ResponseEntity<List<String>> getAllSectorIds() {
        try {
            List<String> sectorIds = kmlService.findAllSectorIds();
            return ResponseEntity.ok(sectorIds);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
    
    @GetMapping("/all-kmls")
    public ResponseEntity<List<Map<String, String>>> getAllKmls() {
        List<Map<String, String>> result = new ArrayList<>();

        try {
            List<String> userTypes = List.of("zh", "rh", "asm", "tsm", "tl", "distributor", "tse");

            for (String userType : userTypes) {
                List<String> sectorIds;
                switch (userType) {
                    case "zh":
                        sectorIds = kmlService.findAllSectorIdsForZh();
                        break;
                    case "rh":
                        sectorIds = kmlService.findAllSectorIdsForRh();
                        break;
                    case "asm":
                        sectorIds = kmlService.findAllSectorIdsForAsm();
                        break;
                    case "tsm":
                        sectorIds = kmlService.findAllSectorIdsForTsm();
                        break;
                    case "tl":
                        sectorIds = kmlService.findAllSectorIdsForTl();
                        break;
                    case "distributor":
                        sectorIds = kmlService.findAllSectorIdsForDistributor();
                        break;
                    case "tse":
                        sectorIds = kmlService.findAllSectorIdsForTse();
                        break;
                    default:
                        continue;
                }

                for (String sectorId : sectorIds) {
                    String kml = null;
                    switch (userType) {
                        case "zh":
                            kml = kmlService.getKMLDataForZh(sectorId);
                            break;
                        case "rh":
                            kml = kmlService.getKMLDataForRh(sectorId);
                            break;
                        case "asm":
                            kml = kmlService.getKMLDataForAsm(sectorId);
                            break;
                        case "tsm":
                            kml = kmlService.getKMLDataForTsm(sectorId);
                            break;
                        case "tl":
                            kml = kmlService.getKMLDataForTl(sectorId);
                            break;
                        case "distributor":
                            kml = kmlService.getKMLDataForDistributor(sectorId);
                            break;
                        case "tse":
                            kml = kmlService.getKMLDataForTse(sectorId);
                            break;
                    }

                    if (kml != null && !kml.isEmpty()) {
                        Map<String, String> item = new HashMap<>();
                        item.put("userType", userType);
                        item.put("sectorId", sectorId);
                        item.put("kmlData", kml);
                        result.add(item);
                    }
                }
            }

            return ResponseEntity.ok(result);

        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

}
