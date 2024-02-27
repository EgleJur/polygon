package org.example;

import org.apache.commons.geometry.euclidean.threed.Vector3D;
import org.apache.commons.geometry.euclidean.threed.rotation.QuaternionRotation;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
          List<Vector2D> inputPolygon = createInputPolygon();

//        List<Vector2D> inputPolygon = new ArrayList<>();
//        inputPolygon.add(new Vector2D(0, 0));
//        inputPolygon.add(new Vector2D(100, 0));
//        inputPolygon.add(new Vector2D(100, 50));
//        inputPolygon.add(new Vector2D(0, 50));

        double slopeAngle = 45;
        double azimuthAngle = 270;

        List<Vector3D> transformedPolygon = create3DPolygon(inputPolygon, slopeAngle, azimuthAngle);
        for (Vector3D vertex : transformedPolygon) {
            System.out.println(vertex);
        }
    }

    private static List<Vector2D> createInputPolygon() {
        Scanner scanner = new Scanner(System.in);
        List<Vector2D> polygon = new ArrayList<>();

        int i = 0;
        while (i < 4) {
            System.out.println("Enter coordinates for point " + (i + 1) + " (x y):");
            double x = scanner.nextDouble();
            double y = scanner.nextDouble();

            boolean pointMatchesPrevious = false;
            for (int j = 0; j < i; j++) {
                if (polygon.get(j).getX() == x && polygon.get(j).getY() == y) {
                    System.out.println("Point can't be equal to previous points");
                    pointMatchesPrevious = true;
                    break;
                }
            }

            if (pointMatchesPrevious) {
                continue;
            }

            polygon.add(new Vector2D(x, y));
            i++;
        }
        scanner.close();

        return polygon;
    }

    public static List<Vector3D> create3DPolygon(List<Vector2D> polygon2D,
                                                 double slopeAngle, double azimuthAngle) {
        List<Vector3D> polygon = new ArrayList<>();
        for (Vector2D vertex : polygon2D) {
            polygon.add(Vector3D.of(vertex.getX(), vertex.getY(), 0.0));
        }
        double slopeAngleRad = Math.toRadians(slopeAngle);

        double height = 0.0;
        if (azimuthAngle == 0 || azimuthAngle == 180) {
            height = Math.tan(slopeAngleRad) * (polygon.get(2).getY() - polygon.get(1).getY());
        } else if (azimuthAngle == 90 || azimuthAngle == 270) {
            height = Math.tan(slopeAngleRad) * (polygon.get(1).getX() - polygon.get(0).getX());
        } else if ((azimuthAngle > 0 && azimuthAngle <= 90) || (azimuthAngle > 180 && azimuthAngle <= 270)){
                        height = Math.tan(slopeAngleRad) * Math.sqrt(Math.pow(polygon.get(2).getX() -
                                polygon.get(0).getX(), 2) + Math.pow(polygon.get(2).getY() -
                                polygon.get(0).getY(), 2));
        }else if ((azimuthAngle > 90 && azimuthAngle <= 180) || (azimuthAngle > 270 && azimuthAngle <= 360)){
            height = Math.tan(slopeAngleRad) * Math.sqrt(Math.pow(polygon.get(1).getX() -
                    polygon.get(3).getX(), 2) + Math.pow(polygon.get(1).getY() -
                    polygon.get(6).getY(), 2));
        }

        //QuaternionRotation azimuthRotation = QuaternionRotation.fromAxisAngle(Vector3D.of(0, 0, 1), Math.toRadians(azimuthAngle));
        QuaternionRotation slopeRotation = QuaternionRotation.fromAxisAngle(Vector3D.of(1, 0, 0), slopeAngleRad);


        List<Vector3D> resultPolygon = new ArrayList<>();
        for (Vector3D vertex : polygon) {
            Vector3D rotatedVertex = slopeRotation.apply(vertex);
            //Vector3D rotatedVertex2 = azimuthRotation.apply(slopeRotation.apply(vertex));
            double x = vertex.getX();
            double y = vertex.getY();
            double z = vertex.getZ();
            if (((azimuthAngle == 0 || azimuthAngle == 360) && (vertex.equals(polygon.get(0)) || vertex.equals(polygon.get(1)))) ||
                    ((azimuthAngle == 90) && (vertex.equals(polygon.get(0)) || vertex.equals(polygon.get(3)))) ||
                    ((azimuthAngle == 180) && (vertex.equals(polygon.get(2)) || vertex.equals(polygon.get(3)))) ||
                    ((azimuthAngle == 270) && (vertex.equals(polygon.get(1)) || vertex.equals(polygon.get(2))))) {

                z += height;

            } else if ((azimuthAngle > 0 && azimuthAngle <= 90) && (vertex.equals(polygon.get(0) )) ||
                            ((azimuthAngle > 90 && azimuthAngle <= 180) && vertex.equals(polygon.get(3))) ||
                            ((azimuthAngle > 180 && azimuthAngle <= 270) && ( vertex.equals(polygon.get(2)))) ||
                            ((azimuthAngle > 270 && azimuthAngle <= 360) && ( vertex.equals(polygon.get(1))))) {
                x = rotatedVertex.getX();
                y = rotatedVertex.getY();
                z += height;
            }

            resultPolygon.add(Vector3D.of(round(x), round(y), round(z)));

        }
        return resultPolygon;
    }

    private static double round(double i) {
        return (Math.round(i * 100.0) / 100.0);
    }

}