package com.braillevision.model;

public class Dot {
    private final double x;
    private final double y;
    private final double radius;
    private final double confidence;

    public Dot(double x, double y, double radius, double confidence) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.confidence = confidence;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getRadius() {
        return radius;
    }

    public double getConfidence() {
        return confidence;
    }
}
