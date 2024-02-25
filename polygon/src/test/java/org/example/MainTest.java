package org.example;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MainTest {

    private List<Map<String, Double>> inputPolygon;

    @BeforeEach
    void setUp() {
        inputPolygon = createInputPolygon();
    }

    @Test
    public void testCreate3DPolygon_Azimuth270_Slope45() {

        double slopeAngle = 45;
        double azimuthAngle = 270;

        List<Map<String, Double>> resultPolygon = Main.create3DPolygon(inputPolygon, slopeAngle, azimuthAngle);

        assertEquals(4, resultPolygon.size());
        assertTrue(comparePoints(resultPolygon.get(0), 0.0, 0.0, 0.0));
        assertTrue(comparePoints(resultPolygon.get(1), 100.0, 0.0, 100.0));
        assertTrue(comparePoints(resultPolygon.get(2), 100.0, 50.0, 100.0));
        assertTrue(comparePoints(resultPolygon.get(3), 0.0, 50.0, 0.0));
    }

    @Test
    public void testCreate3DPolygon_Azimuth180_Slope45() {

        double slopeAngle = 45;
        double azimuthAngle = 180;

        List<Map<String, Double>> resultPolygon = Main.create3DPolygon(inputPolygon, slopeAngle, azimuthAngle);

        assertEquals(4, resultPolygon.size());
        assertTrue(comparePoints(resultPolygon.get(0), 0.0, 0.0, 0.0));
        assertTrue(comparePoints(resultPolygon.get(1), 100.0, 0.0, 0.0));
        assertTrue(comparePoints(resultPolygon.get(2), 100.0, 50.0, 100.0));
        assertTrue(comparePoints(resultPolygon.get(3), 0.0, 50.0, 100.0));
    }
    @Test
    public void testCreate3DPolygon_Azimuth0_Slope45() {

        double slopeAngle = 45;
        double azimuthAngle = 0;

        List<Map<String, Double>> resultPolygon = Main.create3DPolygon(inputPolygon, slopeAngle, azimuthAngle);

        assertEquals(4, resultPolygon.size());
        assertTrue(comparePoints(resultPolygon.get(0), 0.0, 0.0, 100.0));
        assertTrue(comparePoints(resultPolygon.get(1), 100.0, 0.0, 100.0));
        assertTrue(comparePoints(resultPolygon.get(2), 100.0, 50.0, 00.0));
        assertTrue(comparePoints(resultPolygon.get(3), 0.0, 50.0, 0.0));
    }
    @Test
    public void testCreate3DPolygon_Azimuth90_Slope45() {

        double slopeAngle = 45;
        double azimuthAngle = 90;

        List<Map<String, Double>> resultPolygon = Main.create3DPolygon(inputPolygon, slopeAngle, azimuthAngle);

        assertEquals(4, resultPolygon.size());
        assertTrue(comparePoints(resultPolygon.get(0), 0.0, 0.0, 100.0));
        assertTrue(comparePoints(resultPolygon.get(1), 100.0, 0.0, 0.0));
        assertTrue(comparePoints(resultPolygon.get(2), 100.0, 50.0, 0.0));
        assertTrue(comparePoints(resultPolygon.get(3), 0.0, 50.0, 100.0));
    }

    private List<Map<String, Double>> createInputPolygon() {
        return List.of(
                Main.createPoint(0.0, 0.0, 0.0),
                Main.createPoint(100.0, 0.0, 0.0),
                Main.createPoint(100.0, 50.0, 0.0),
                Main.createPoint(0.0, 50.0, 0.0)
        );
    }
    private boolean comparePoints(Map<String, Double> point, double x, double y, double z) {
        return point.get("x").equals(x) && point.get("y").equals(y) && point.get("z").equals(z);
    }
}