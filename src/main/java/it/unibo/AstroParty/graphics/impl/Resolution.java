package it.unibo.AstroParty.graphics.impl;

public enum Resolution {
    //TODO: set all the possible resolutions
    R1("320x320", 320, 320);

    private String name;
    private double height, width;

    private Resolution (String name, double height, double width) {
        this.name = name;
        this.height = height;
        this.width = width;
    }

    public String getName() {
        return name;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }
}
