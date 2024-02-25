package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Polygon inputPolygon = createInputPolygon();

//        Polygon inputPolygon = new Polygon();
//        inputPolygon.addPoint(new Point(0.0, 0.0, 0.0));
//        inputPolygon.addPoint(new Point(100.0, 0.0, 0.0));
//        inputPolygon.addPoint(new Point(100.0, 50.0, 0.0));
//        inputPolygon.addPoint(new Point(0.0, 50.0, 0.0));

        if (inputPolygon.getPoints().isEmpty() || inputPolygon.size() < 4) {
            throw new IllegalArgumentException("Polygon must contain at least 4 points.");
        }

        double slopeAngle = 45;
        double azimuthAngle = 270;

        Polygon resultPolygon = create3DPolygon(inputPolygon, slopeAngle, azimuthAngle);
        System.out.println(resultPolygon);
    }

    private static Polygon createInputPolygon() {
        Scanner scanner = new Scanner(System.in);
        Polygon polygon = new Polygon();

        int i = 0;
        while (i < 4) {
            System.out.println("Enter coordinates for point " + (i + 1) + " (x y z):");
            double x = scanner.nextDouble();
            double y = scanner.nextDouble();
            double z = scanner.nextDouble();

            boolean pointMatchesPrevious = false;
            for (int j = 0; j < i; j++) {
                if (polygon.getPoint(j).getX() == x && polygon.getPoint(j).getY() == y &&
                        polygon.getPoint(j).getZ() == z) {
                    System.out.println("Point can't be equal to previous points");
                    pointMatchesPrevious = true;
                    break;
                }
            }

            if (pointMatchesPrevious) {
                continue;
            }

            polygon.addPoint(new Point(x, y, z));
            i++;
        }
        scanner.close();

        return polygon;
    }

    public static Polygon create3DPolygon(Polygon polygon,
                                          double slopeAngle, double azimuthAngle) {

        double slopeAngleRad = Math.toRadians(slopeAngle);

        Polygon resultPolygon = new Polygon();

        double height = Math.tan(slopeAngleRad) * Math.sqrt(Math.pow(polygon.getPoint(1).getX() -
                        polygon.getPoint(0).getX(), 2) + Math.pow(polygon.getPoint(1).getY() -
                                polygon.getPoint(0).getY(), 2));

        for (int i = 0; i < polygon.size(); i++) {
            Point point = polygon.getPoint(i);
            double z = point.getZ();
            double rotatedX = point.getX();
            double rotatedY = point.getY();
            if (((azimuthAngle == 0 || (azimuthAngle > 270 && azimuthAngle <= 360)) && (i == 0 || i == 1)) ||
                    ((azimuthAngle > 0 && azimuthAngle <= 90) && (i == 0 || i == 3)) ||
                    ((azimuthAngle > 90 && azimuthAngle <= 180) && (i == 2 || i == 3)) ||
                    ((azimuthAngle > 180 && azimuthAngle <= 270) && (i == 1 || i == 2))) {
                z += height;
            }

            resultPolygon.addPoint(new Point(Math.round(rotatedX * 100.0) / 100.0,
                    Math.round(rotatedY * 100.0) / 100.0,
                    Math.round(z * 100.0) / 100.0));
        }

        return resultPolygon;
    }

}