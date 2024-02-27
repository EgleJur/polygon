package org.example;


import org.apache.commons.geometry.euclidean.threed.Vector3D;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    private List<Vector2D> inputPolygon;

    @BeforeEach
    void setUp() {
        inputPolygon = createInputPolygon();
    }

    @Test
    public void testCreate3DPolygon_Azimuth0_Slope45() {

        double slopeAngle = 45;
        double azimuthAngle = 0;

        List<Vector3D> resultPolygon = Main.create3DPolygon(inputPolygon, slopeAngle, azimuthAngle);

        assertEquals(4, resultPolygon.size());

        assertEquals(Vector3D.of(0.0, 0.0, 50.0), resultPolygon.get(0));
        assertEquals(Vector3D.of(100.0, 0.0, 50.0), resultPolygon.get(1));
        assertEquals(Vector3D.of(100.0, 50.0, 00.0), resultPolygon.get(2));
        assertEquals(Vector3D.of( 0.0, 50.0, 0.0), resultPolygon.get(3));

    }

    @Test
    public void testCreate3DPolygon_Azimuth90_Slope45() {

        double slopeAngle = 45;
        double azimuthAngle = 90;

        List<Vector3D> resultPolygon = Main.create3DPolygon(inputPolygon, slopeAngle, azimuthAngle);

        assertEquals(4, resultPolygon.size());

        assertEquals(Vector3D.of(0.0, 0.0, 100.0), resultPolygon.get(0));
        assertEquals(Vector3D.of(100.0, 0.0, 0.0), resultPolygon.get(1));
        assertEquals(Vector3D.of(100.0, 50.0, 00.0), resultPolygon.get(2));
        assertEquals(Vector3D.of( 0.0, 50.0, 100.0), resultPolygon.get(3));

    }

    @Test
    public void testCreate3DPolygon_Azimuth180_Slope45() {

        double slopeAngle = 45;
        double azimuthAngle = 180;

        List<Vector3D> resultPolygon = Main.create3DPolygon(inputPolygon, slopeAngle, azimuthAngle);

        assertEquals(4, resultPolygon.size());

        assertEquals(Vector3D.of(0.0, 0.0, 0.0), resultPolygon.get(0));
        assertEquals(Vector3D.of(100.0, 0.0, 0.0), resultPolygon.get(1));
        assertEquals(Vector3D.of(100.0, 50.0, 50.0), resultPolygon.get(2));
        assertEquals(Vector3D.of( 0.0, 50.0, 50.0), resultPolygon.get(3));

    }
    @Test
    public void testCreate3DPolygon_Azimuth270_Slope45() {

        double slopeAngle = 45;
        double azimuthAngle = 270;

        List<Vector3D> resultPolygon = Main.create3DPolygon(inputPolygon, slopeAngle, azimuthAngle);

        assertEquals(4, resultPolygon.size());

        assertEquals(Vector3D.of(0.0, 0.0, 0.0), resultPolygon.get(0));
        assertEquals(Vector3D.of(100.0, 0.0, 100.0), resultPolygon.get(1));
        assertEquals(Vector3D.of(100.0, 50.0, 100.0), resultPolygon.get(2));
        assertEquals(Vector3D.of( 0.0, 50.0, 0.0), resultPolygon.get(3));

    }

    @Test
    public void testCreate3DPolygon_Azimuth180_Slope60() {

        double slopeAngle = 60;
        double azimuthAngle = 180;

        List<Vector3D> resultPolygon = Main.create3DPolygon(inputPolygon, slopeAngle, azimuthAngle);

        assertEquals(4, resultPolygon.size());

        assertEquals(Vector3D.of(0.0, 0.0, 0.0), resultPolygon.get(0));
        assertEquals(Vector3D.of(100.0, 0.0, 0.0), resultPolygon.get(1));
        assertEquals(Vector3D.of(100.0, 50.0, 86.6), resultPolygon.get(2));
        assertEquals(Vector3D.of( 0.0, 50.0, 86.6), resultPolygon.get(3));

    }


    private List<Vector2D> createInputPolygon() {
        List<Vector2D> polygon = new ArrayList<>();
        polygon.add(new Vector2D(0, 0));
        polygon.add(new Vector2D(100, 0));
        polygon.add(new Vector2D(100, 50));
        polygon.add(new Vector2D(0, 50));
        return polygon;
    }

    private boolean comparePoints(Point point, double x, double y, double z) {
        return point.getX() == x && point.getY() == y && point.getZ() == z;
    }
}