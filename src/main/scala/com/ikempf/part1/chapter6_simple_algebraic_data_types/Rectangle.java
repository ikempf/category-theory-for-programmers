package com.ikempf.part1.chapter6_simple_algebraic_data_types;

public class Rectangle implements Shape {

    private double w;
    private double h;

    public Rectangle(double w, double h) {
        this.w = w;
        this.h = h;
    }

    @Override
    public double area() {
        return w * h;
    }

    @Override
    public double circ() {
        return 2 * (w  + h);
    }

}
