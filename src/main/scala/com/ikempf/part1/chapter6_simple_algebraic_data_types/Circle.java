package com.ikempf.part1.chapter6_simple_algebraic_data_types;

public class Circle implements Shape {

    private double r;

    public Circle(double r) {
        this.r = r;
    }

    @Override
    public double area() {
        return Math.PI * r * r;
    }

    @Override
    public double circ() {
        return 2 * Math.PI * r ;
    }

}
