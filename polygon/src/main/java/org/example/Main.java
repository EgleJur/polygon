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

        List<Map<String, Double>> resultPolygon = create3DPolygon(inputPolygon, slopeAngle, azimuthAngle);

        System.out.println("Resulting 3D polygon:");
        for (Map<String, Double> point : resultPolygon) {
            System.out.printf("(%.2f, %.2f, %.2f)%n", point.get("x"), point.get("y"), point.get("z"));
        }
    }
        public static Map<String, Double> createPoint(double x, double y, double z) {
            Map<String, Double> point = new HashMap<>();
            point.put("x", x);
            point.put("y", y);
            point.put("z", z);
            return point;
        }

    public static List<Map<String, Double>> create3DPolygon(List<Map<String, Double>> polygon, double slopeAngle, double azimuthAngle) {

        double slopeAngleRad = Math.toRadians(slopeAngle);
        double azimuthAngleRad = Math.toRadians(azimuthAngle);

        List<Map<String, Double>> resultPolygon = new ArrayList<>();

        double height = Math.tan(slopeAngleRad) * polygon.get(0).get("x");

        for (Map<String, Double> point : polygon) {
            double rotatedX = point.get("x") * Math.cos(azimuthAngleRad) - point.get("y") * Math.sin(azimuthAngleRad);
            double rotatedY = point.get("x") * Math.sin(azimuthAngleRad) + point.get("y") * Math.cos(azimuthAngleRad);
            double z = point.get("z") + height;
            resultPolygon.add(createPoint(rotatedX, rotatedY, z));
        }

        return resultPolygon;
    }

}