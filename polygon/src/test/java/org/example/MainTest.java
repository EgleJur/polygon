package org.example;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    private Polygon inputPolygon;

    @BeforeEach
    void setUp() {
        inputPolygon = createInputPolygon();
    }

    @Test
    public void testCreate3DPolygon_Azimuth0_Slope45() {

        double slopeAngle = 45;
        double azimuthAngle = 0;

        Polygon resultPolygon = Main.create3DPolygon(inputPolygon, slopeAngle, azimuthAngle);

        assertEquals(4, resultPolygon.size());
        assertTrue(comparePoints(resultPolygon.getPoint(0), 0.0, 0.0, 50.0));
        assertTrue(comparePoints(resultPolygon.getPoint(1), 100.0, 0.0, 50.0));
        assertTrue(comparePoints(resultPolygon.getPoint(2), 100.0, 50.0, 00.0));
        assertTrue(comparePoints(resultPolygon.getPoint(3), 0.0, 50.0, 0.0));
    }

    @Test
    public void testCreate3DPolygon_Azimuth90_Slope45() {

        double slopeAngle = 45;
        double azimuthAngle = 90;

        Polygon resultPolygon = Main.create3DPolygon(inputPolygon, slopeAngle, azimuthAngle);

        assertEquals(4, resultPolygon.size());
        assertTrue(comparePoints(resultPolygon.getPoint(0), 0.0, 0.0, 100.0));
        assertTrue(comparePoints(resultPolygon.getPoint(1), 100.0, 0.0, 0.0));
        assertTrue(comparePoints(resultPolygon.getPoint(2), 100.0, 50.0, 0.0));
        assertTrue(comparePoints(resultPolygon.getPoint(3), 0.0, 50.0, 100.0));
    }

    @Test
    public void testCreate3DPolygon_Azimuth180_Slope45() {

        double slopeAngle = 45;
        double azimuthAngle = 180;

        Polygon resultPolygon = Main.create3DPolygon(inputPolygon, slopeAngle, azimuthAngle);

        assertEquals(4, resultPolygon.size());
        assertTrue(comparePoints(resultPolygon.getPoint(0), 0.0, 0.0, 0.0));
        assertTrue(comparePoints(resultPolygon.getPoint(1), 100.0, 0.0, 0.0));
        assertTrue(comparePoints(resultPolygon.getPoint(2), 100.0, 50.0, 50.0));
        assertTrue(comparePoints(resultPolygon.getPoint(3), 0.0, 50.0, 50.0));
    }
    @Test
    public void testCreate3DPolygon_Azimuth270_Slope45() {

        double slopeAngle = 45;
        double azimuthAngle = 270;

        Polygon resultPolygon = Main.create3DPolygon(inputPolygon, slopeAngle, azimuthAngle);

        assertEquals(4, resultPolygon.size());
        assertTrue(comparePoints(resultPolygon.getPoint(0), 0.0, 0.0, 0.0));
        assertTrue(comparePoints(resultPolygon.getPoint(1), 100.0, 0.0, 100.0));
        assertTrue(comparePoints(resultPolygon.getPoint(2), 100.0, 50.0, 100.0));
        assertTrue(comparePoints(resultPolygon.getPoint(3), 0.0, 50.0, 0.0));
    }

    @Test
    public void testCreate3DPolygon_Azimuth180_Slope60() {

        double slopeAngle = 60;
        double azimuthAngle = 180;

        Polygon resultPolygon = Main.create3DPolygon(inputPolygon, slopeAngle, azimuthAngle);

        assertEquals(4, resultPolygon.size());
        assertTrue(comparePoints(resultPolygon.getPoint(0), 0.0, 0.0, 0.0));
        assertTrue(comparePoints(resultPolygon.getPoint(1), 100.0, 0.0, 0.0));
        assertTrue(comparePoints(resultPolygon.getPoint(2), 100.0, 50.0, 86.6));
        assertTrue(comparePoints(resultPolygon.getPoint(3), 0.0, 50.0, 86.6));
    }


    private Polygon createInputPolygon() {
        Polygon polygon = new Polygon();
        polygon.addPoint(new Point(0.0, 0.0, 0.0));
        polygon.addPoint(new Point(100.0, 0.0, 0.0));
        polygon.addPoint(new Point(100.0, 50.0, 0.0));
        polygon.addPoint(new Point(0.0, 50.0, 0.0));
        return polygon;
    }

    private boolean comparePoints(Point point, double x, double y, double z) {
        return point.getX() == x && point.getY() == y && point.getZ() == z;
    }
}