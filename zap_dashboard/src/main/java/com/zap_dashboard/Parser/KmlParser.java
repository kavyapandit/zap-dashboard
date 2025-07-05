package com.zap_dashboard.Parser;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import java.io.InputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jdom2.Namespace;

public class KmlParser {

    public static List<String> parseKmlFile(InputStream inputStream) throws JDOMException, IOException {
        List<String> coordinates = new ArrayList<>();
        SAXBuilder saxBuilder = new SAXBuilder();
        Document document = saxBuilder.build(inputStream);
        Element rootElement = document.getRootElement();
        
        Namespace kmlNs = rootElement.getNamespace();
        Element docElement = rootElement.getChild("Document", kmlNs);
        
        if (docElement != null) {
            processElement(docElement, kmlNs, coordinates);
        } else {
            processElement(rootElement, kmlNs, coordinates);
        }

        System.out.println("Extracted Coordinates: " + coordinates); // Debugging
        return coordinates;
    }

    private static void processElement(Element element, Namespace kmlNs, List<String> coordinates) {
        if (element == null) return;

        // Extract coordinates from <coordinates> tags directly
        Element coordElement = element.getChild("coordinates", kmlNs);
        if (coordElement != null) {
            addCoordinates(coordElement.getTextTrim(), coordinates);
        }

        // Extract from <Placemark>
        List<Element> placemarks = element.getChildren("Placemark", kmlNs);
        for (Element placemark : placemarks) 
        {
            extractFromGeometry(placemark, kmlNs, coordinates);
        }

        // Extract from <Folder>
        List<Element> folders = element.getChildren("Folder", kmlNs);
        for (Element folder : folders) 
        {
            processElement(folder, kmlNs, coordinates);
        }
    }

    private static void extractFromGeometry(Element placemark, Namespace kmlNs, List<String> coordinates) 
    {
        if (placemark == null) return;
        Element point = placemark.getChild("Point", kmlNs);
        Element lineString = placemark.getChild("LineString", kmlNs);
        Element polygon = placemark.getChild("Polygon", kmlNs);
        Element multiGeometry = placemark.getChild("MultiGeometry", kmlNs);

        if (point != null) extractCoordinates(point, kmlNs, coordinates);
        if (lineString != null) extractCoordinates(lineString, kmlNs, coordinates);
        if (polygon != null) extractPolygonCoordinates(polygon, kmlNs, coordinates);
        if (multiGeometry != null) {
            for (Element child : multiGeometry.getChildren()) 
            {
                extractCoordinates(child, kmlNs, coordinates);
            }
        }
    }

    private static void extractCoordinates(Element geometryElement, Namespace kmlNs, List<String> coordinates) {
        if (geometryElement == null) return;
        Element coordElement = geometryElement.getChild("coordinates", kmlNs);
        if (coordElement != null) {
            addCoordinates(coordElement.getTextTrim(), coordinates);
        }
    }

    private static void extractPolygonCoordinates(Element polygon, Namespace kmlNs, List<String> coordinates) 
    {
        Element outerBoundary = polygon.getChild("outerBoundaryIs", kmlNs);
        if (outerBoundary != null) 
        {
            Element linearRing = outerBoundary.getChild("LinearRing", kmlNs);
            if (linearRing != null) 
            {
                extractCoordinates(linearRing, kmlNs, coordinates);
            }
        }
    }

    private static void addCoordinates(String coordStr, List<String> coordinates) 
    {
        if (coordStr == null || coordStr.isEmpty()) return;

        String[] points = coordStr.trim().split("\\s+");
        for (String point : points) {
            String[] coords = point.split(",");
            if (coords.length >= 2) {
                try 
                {
                    double lon = Double.parseDouble(coords[0]);
                    double lat = Double.parseDouble(coords[1]);
                    coordinates.add("[" + lat + "," + lon + "]");
                } 
                catch (NumberFormatException e) 
                {
                    System.err.println("Failed to parse coordinate: " + point);
                }
            }
        }
    }
}
