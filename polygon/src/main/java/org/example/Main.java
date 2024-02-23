package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        List<Map<String, Double>> inputPolygon = new ArrayList<>();
        inputPolygon.add(createPoint(0.0, 0.0, 0.0));
        inputPolygon.add(createPoint(100.0, 0.0, 0.0));
        inputPolygon.add(createPoint(100.0, 50.0, 0.0));
        inputPolygon.add(createPoint(0.0, 50.0, 0.0));

        double slopeAngle = 45;
        double azimuthAngle = 270;
    }
        public static Map<String, Double> createPoint(double x, double y, double z) {
            Map<String, Double> point = new HashMap<>();
            point.put("x", x);
            point.put("y", y);
            point.put("z", z);
            return point;
        }

}