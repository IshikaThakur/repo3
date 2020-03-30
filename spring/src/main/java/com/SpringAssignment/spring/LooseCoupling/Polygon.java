package com.SpringAssignment.spring.LooseCoupling;

public class Polygon {
    IPolygon iPolygon;     //reference of interface is being created
    public Polygon(IPolygon ipl)      //Prameterized Constructor
    {
        this.iPolygon=ipl;
    }
    public double calcArea()
    {
        return iPolygon.getArea();
    }

    public double getAreaOfPolygon()
    {
        //String type;
       double area=calcArea();
       return area;
    }
}
