package com.SpringAssignment.spring.LooseCoupling;
// Ques 2. Write a program to demonstrate Loosely Coupled code

public class LooseCpl {
    public static void main(String[] args) {
        Circle c1=new Circle(8.0);
        Polygon p1=new Polygon(c1);
        System.out.println("Result is==========================================================");
        System.out.println("Area of Polygon is="+p1.getAreaOfPolygon());
        //For Triangle
      Triangle t1=new Triangle(12,20);
        Polygon p2=new Polygon(t1);
        System.out.println("Area of Polygon is="+p2.getAreaOfPolygon());

    }
}
