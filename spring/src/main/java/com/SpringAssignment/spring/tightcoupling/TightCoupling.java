package com.SpringAssignment.spring.tightcoupling;
//Ques1 :Wite a program to demonstrate Tightly Coupled code
public class TightCoupling {
    Cars cars= new Cars();
    void setVehicles()
    {
        cars.display();
    }
    public static void main(String[] args)
    {
        TightCoupling tightCoupling=new TightCoupling();
      tightCoupling.setVehicles();
    }
}


