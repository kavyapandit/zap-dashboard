package com.zap_dashboard.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zap_dashboard.Repository.KmlCoordinatesRepository;
import com.zap_dashboard.entity.KmlCoordinates;

import java.util.List;

@Service
public class KmlCoordinatesService {
    @Autowired
    private KmlCoordinatesRepository repository;
    
    public void saveCoordinates(String filename, List<String> coordinatesList) {
        for (String coord : coordinatesList) {
            KmlCoordinates kmlCoordinate = new KmlCoordinates();
            kmlCoordinate.setFileName(filename);
            kmlCoordinate.setCoordinates(coord);
            repository.save(kmlCoordinate);
        }
    }
    
    public void saveKmlCoordinates(KmlCoordinates kmlCoordinates) {
        repository.save(kmlCoordinates);
    }
}

