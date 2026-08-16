package com.braillevision.model;

import java.util.List;

public class Cell {
    private final List<Dot> dots;
    private final String binaryPattern;
    private final double boundingBoxX;
    private final double boundingBoxY;
    private final double boundingBoxWidth;
    private final double boundingBoxHeight;

    public Cell(List<Dot> dots, String binaryPattern, double x, double y, double width, double height) {
        this.dots = dots;
        this.binaryPattern = binaryPattern;
        this.boundingBoxX = x;
        this.boundingBoxY = y;
        this.boundingBoxWidth = width;
        this.boundingBoxHeight = height;
    }

    public List<Dot> getDots() {
        return dots;
    }

    public String getBinaryPattern() {
        return binaryPattern;
    }

    public double getBoundingBoxX() {
        return boundingBoxX;
    }

    public double getBoundingBoxY() {
        return boundingBoxY;
    }

    public double getBoundingBoxWidth() {
        return boundingBoxWidth;
    }

    public double getBoundingBoxHeight() {
        return boundingBoxHeight;
    }
}
