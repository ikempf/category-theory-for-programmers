package com.ikempf.part1.chapter6_simple_algebraic_data_types;

public class Square implements Shape {

    private double d;

    public Square(double d) {
        this.d = d;
    }

    @Override
    public double area() {
        return d * d;
    }

    @Override
    public double circ() {
        return 4 * d;
    }
}
