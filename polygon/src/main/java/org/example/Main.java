package org.example;

public class Main {
    public static void main(String[] args) {
        Polygon inputPolygon = createInputPolygon();

        double slopeAngle = 45;
        double azimuthAngle = 270;

        Polygon resultPolygon = create3DPolygon(inputPolygon, slopeAngle, azimuthAngle);
        System.out.println(resultPolygon);
    }

    private static Polygon createInputPolygon() {
        Polygon polygon = new Polygon();
        polygon.addPoint(new Point(40.0, 0.0, 0.0));
        polygon.addPoint(new Point(90.0, 50.0, 0.0));
        polygon.addPoint(new Point(50.0, 90.0, 0.0));
        polygon.addPoint(new Point(0.0, 40.0, 0.0));
        if (polygon.getPoints().isEmpty() || polygon.size() < 4) {
            throw new IllegalArgumentException("Polygon must contain at least 4 points.");
        }
        return polygon;
    }

    public static Polygon create3DPolygon(Polygon polygon,
                                          double slopeAngle, double azimuthAngle) {

        double slopeAngleRad = Math.toRadians(slopeAngle);
        double azimuthAngleRad = Math.toRadians(azimuthAngle);

        Polygon resultPolygon = new Polygon();

        double height = Math.tan(slopeAngleRad) *
                Math.sqrt(Math.pow(polygon.getPoint(1).getX() -
                        polygon.getPoint(0).getX(), 2) +
                        Math.pow(polygon.getPoint(1).getY() -
                                polygon.getPoint(0).getY(), 2));

        for (int i = 0; i < polygon.size(); i++) {
            Point point = polygon.getPoint(i);
            double x = point.getX();
            double y = point.getY();
            double z = point.getZ();
            double rotatedX = x;
            double rotatedY = y;
            if ((azimuthAngle == 0 && (i == 0 || i == 1)) ||
                    ((azimuthAngle > 0 && azimuthAngle <= 90) && (i == 0 || i == 3)) ||
                    ((azimuthAngle > 90 && azimuthAngle <= 180) && (i == 2 || i == 3)) ||
                    ((azimuthAngle > 180 && azimuthAngle <= 270) && (i == 1 || i == 2)) ||
                    ((azimuthAngle > 270 && azimuthAngle <= 360) && (i == 0 || i == 1))) {

                z += height;
            }

            resultPolygon.addPoint(new Point(Math.round(rotatedX * 100.0) / 100.0,
                    Math.round(rotatedY * 100.0) / 100.0,
                    Math.round(z * 100.0) / 100.0));
        }

        return resultPolygon;
    }

}