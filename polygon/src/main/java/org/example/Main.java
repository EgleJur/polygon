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
            System.out.printf("(%.0f, %.0f, %.0f)%n", point.get("x"), point.get("y"), point.get("z"));
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

        double height = Math.tan(slopeAngleRad) * Math.sqrt(Math.pow(polygon.get(1).get("x") - polygon.get(0).get("x"), 2) + Math.pow(polygon.get(1).get("y") - polygon.get(0).get("y"), 2));

        for (int i = 0; i < polygon.size(); i++) {
            Map<String, Double> point = polygon.get(i);
            double x = point.get("x");
            double y = point.get("y");
            double z = point.get("z");

            if ((azimuthAngle == 0 && (i == 0 || i == 1)) ||
                    (azimuthAngle == 90 && (i == 0 || i == 3)) ||
                    (azimuthAngle == 180 && (i == 2 || i == 3)) ||
                    (azimuthAngle == 270 && (i == 1 || i == 2))) {
                z += height;
            }

            double rotatedX = x;
            double rotatedY = y;
            if (azimuthAngle != 0 && azimuthAngle != 90 && azimuthAngle != 180 && azimuthAngle != 270) {
                rotatedX = x * Math.cos(azimuthAngleRad) - y * Math.sin(azimuthAngleRad);
                rotatedY = x * Math.sin(azimuthAngleRad) + y * Math.cos(azimuthAngleRad);
            }

            resultPolygon.add(createPoint(Math.round(rotatedX * 100.0) / 100.0,
                    Math.round(rotatedY * 100.0) / 100.0,
                    Math.round(z * 100.0) / 100.0));
        }

        return resultPolygon;
    }

}