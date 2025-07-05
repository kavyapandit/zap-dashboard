package com.zap_dashboard.Controller;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.zap_dashboard.entity.KmlCoordinates;
import com.zap_dashboard.Parser.KmlParser;
import com.zap_dashboard.Repository.KmlCoordinatesRepository;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@RestController
@RequestMapping("/api/upload")
@CrossOrigin(origins = "http://localhost:3000")
public class ZipUploadController {

    @Autowired
    private KmlCoordinatesRepository kmlCoordinatesRepository;

    @PostMapping
    public UploadResponse handleFileUpload(
            @RequestParam("file") MultipartFile file,
            @RequestParam("state") String state,
            @RequestParam("district") String district
    ) {
        List<String> allCoordinates = new ArrayList<>();
        List<String> processedFiles = new ArrayList<>();

        try {
            System.out.println("Uploading: " + file.getOriginalFilename());
            System.out.println("State: " + state + ", District: " + district);

            try (InputStream inputStream = file.getInputStream();
                 ZipInputStream zipInputStream = new ZipInputStream(inputStream)) {

                ZipEntry entry;
                boolean foundKmlFile = false;

                while ((entry = zipInputStream.getNextEntry()) != null) {
                    if (entry.getName().endsWith(".kml")) {
                        foundKmlFile = true;
                        String kmlFileName = entry.getName();
                        byte[] kmlBytes = IOUtils.toByteArray(zipInputStream);
                        
                        try (InputStream kmlInputStream = new ByteArrayInputStream(kmlBytes)) {
                            List<String> coordinatesList = KmlParser.parseKmlFile(kmlInputStream);
                            
                            // Store each KML file as a separate entry
                            KmlCoordinates kmlCoordinates = new KmlCoordinates();
                            kmlCoordinates.setFileName(kmlFileName);
                            kmlCoordinates.setState(state.trim());
                            kmlCoordinates.setDistrict(district.trim());
                            kmlCoordinates.setCoordinates(coordinatesList.toString());
                            kmlCoordinatesRepository.save(kmlCoordinates);
                            
                            // Add to the consolidated list for response
                            allCoordinates.addAll(coordinatesList);
                            processedFiles.add(kmlFileName);
                        }
                    }
                }

                if (!foundKmlFile) {
                    return new UploadResponse("No KML files found!", new ArrayList<>(), new ArrayList<>());
                }
            }

            return new UploadResponse(file.getOriginalFilename() + " processed successfully", allCoordinates, processedFiles);

        } catch (Exception e) {
            e.printStackTrace();
            return new UploadResponse("Error: " + e.getMessage(), new ArrayList<>(), new ArrayList<>());
        }
    }

    public static class UploadResponse {
        private String message;
        private List<String> coordinates;
        private List<String> processedFiles;

        public UploadResponse(String message, List<String> coordinates, List<String> processedFiles) {
            this.message = message;
            this.coordinates = coordinates;
            this.processedFiles = processedFiles;
        }

        public String getMessage() { return message; }
        public List<String> getCoordinates() { return coordinates; }
        public List<String> getProcessedFiles() { return processedFiles; }
    }
}