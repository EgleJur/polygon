package org.example;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Polygon {
    private List<Point> points;

    public Polygon() {
        points = new ArrayList<>();
    }

    public void addPoint(Point point) {
        points.add(point);
    }

    public int size() {
        return points.size();
    }

    public Point getPoint(int i) {
        return points.get(i);
    }

    @Override
    public String toString() {
        return "Resulting polygon: " + points;
    }
}
