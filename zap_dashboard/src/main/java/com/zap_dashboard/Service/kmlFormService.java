package com.zap_dashboard.Service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

import com.zap_dashboard.Repository.*;
import com.zap_dashboard.entity.*;

@Service
public class kmlFormService {

    @Autowired private kmlasmRepository asmRepository;
    @Autowired private kmldistributorRepository distributorRepository;
    @Autowired private kmlrhRepository rhRepository;
    @Autowired private kmltlRepository tlRepository;
    @Autowired private kmltseRepository tseRepository;
    @Autowired private kmltsmRepository tsmRepository;
    @Autowired private kmlzhRepository zhRepository;
    @Autowired 
    private stateZoneRepository statezoneRepository;

    // ✅ Extract coordinates from KML file
    private String extractCoordinatesFromKML(byte[] kmlBytes) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputStream inputStream = new ByteArrayInputStream(kmlBytes);
        Document doc = builder.parse(inputStream);
        NodeList coordinatesNodes = doc.getElementsByTagName("coordinates");

        StringBuilder coordinates = new StringBuilder();
        for (int i = 0; i < coordinatesNodes.getLength(); i++) {
            coordinates.append(coordinatesNodes.item(i).getTextContent().trim()).append("|");
        }

        return coordinates.toString();
    }

    // ✅ Process KML file and save to DB
    public String processKmlFile(String formType, MultipartFile file, String state, 
                                 String sectorId, String location, String requestedBy, 
                                 String district, String tier) throws IOException {
        try {
            String coordinates = extractCoordinatesFromKML(file.getBytes());

            switch(formType) {
                case "ASM":
                    kmlasmEntity asm = new kmlasmEntity();
                    asm.setState(state);
                    asm.setAsmSectorId(sectorId);
                    asm.setAsmCoordinates(coordinates);
                    asm.setStatus("vacant");
                    asmRepository.save(asm);
                    break;
                case "Distributor":
                    kmldistributorEntity distributor = new kmldistributorEntity();
                    distributor.setState(state);
                    distributor.setDistributorSectorId(sectorId);
                    distributor.setDistributorCoordinates(coordinates);
                    distributor.setLocationName(location);
                    distributor.setRequestedBy(requestedBy);
                    distributor.setDistrictCode(district);
                    distributor.setStatus("vacant");
                    Optional<String> zoneOpt = statezoneRepository.findZoneByStateName(state);
                    zoneOpt.ifPresent(distributor::setZone);
                    distributorRepository.save(distributor);
                    break;
                case "ZH":
                    kmlzhEntity zh = new kmlzhEntity();
                    zh.setZhSectorid(sectorId);
                    zh.setZhCoordinates(coordinates);
                    zh.setStatus("vacant");
                    Optional<String> zoneOpt1 = statezoneRepository.findZoneByStateName(state);
                    zoneOpt1.ifPresent(zh::setZone); // Set zone if present

                    zhRepository.save(zh);
                    break;
                case "RH":
                    kmlrhEntity rh = new kmlrhEntity();
                    rh.setState(state);
                    rh.setRhSectorid(sectorId);
                    rh.setRhCoordinates(coordinates);
                    rh.setStatus("vacant");
                    rhRepository.save(rh);
                    break;
                case "TL":
                    kmltlEntity tl = new kmltlEntity();
                    tl.setState(state);
                    tl.setTlSectorid(sectorId);
                    tl.setTlCoordinates(coordinates);
                    tl.setLocationName(location);
                    tl.setAnnexure(tier);
                    tlRepository.save(tl);
                    break;
                case "TSE":
                    kmltseEntity tse = new kmltseEntity();
                    tse.setState(state);
                    tse.setTseSectorid(sectorId);
                    tse.setTseCoordinates(coordinates);
                    tse.setLocationName(location);
                    tse.setTier(tier);
                    tseRepository.save(tse);
                    break;
                case "TSM":
                    kmltsmEntity tsm = new kmltsmEntity();
                    tsm.setState(state);
                    tsm.setDistrict(district);
                    tsm.setTsmSectorid(sectorId);
                    tsm.setTsmCoordinates(coordinates);
                    tsm.setStatus("vacant");
                    tsmRepository.save(tsm);
                    break;
            }

            return "KML file processed successfully";
        } catch (Exception e) {
            throw new IOException("Error processing KML file: " + e.getMessage());
        }
    }

    // ✅ Fetch coordinates by sectorId from DB
    public Optional<String> getCoordinatesBySectorId(String sectorId) {
        Optional<String> coordinates;

        coordinates = zhRepository.findCoordinatesByzhSectorId(sectorId);
        if (coordinates.isPresent()) return coordinates;

        coordinates = rhRepository.findCoordinatesByrhSectorId(sectorId);
        if (coordinates.isPresent()) return coordinates;

        coordinates = asmRepository.findCoordinatesByasmSectorId(sectorId);
        if (coordinates.isPresent()) return coordinates;

        coordinates = tsmRepository.findCoordinatesBytsmSectorId(sectorId);
        if (coordinates.isPresent()) return coordinates;

        coordinates = tlRepository.findCoordinatesBytlSectorId(sectorId);
        if (coordinates.isPresent()) return coordinates;

        coordinates = tseRepository.findCoordinatesBytseSectorId(sectorId);
        if (coordinates.isPresent()) return coordinates;

        coordinates = distributorRepository.findCoordinatesBydisSectorId(sectorId);
        return distributorRepository.findCoordinatesBydisSectorId(sectorId);
    }
	
}
