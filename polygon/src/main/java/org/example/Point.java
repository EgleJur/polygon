package org.example;

import lombok.Data;

@Data
public class Point {
    private double x;
    private double y;
    private double z;

    public Point(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public String toString() {
        return "(" + x +
                ", " + y +
                ", " + z +
                ")";
    }
}