package com.SpringAssignment.spring.LooseCoupling;

public class Circle implements IPolygon {
private double radius;
public Circle(double radius)
{
    this.radius=radius;
}
@Override
     public  double getArea()
{
    return (22*radius*radius)/7;
}
}
