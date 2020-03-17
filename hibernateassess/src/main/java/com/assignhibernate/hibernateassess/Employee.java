package com.assignhibernate.hibernateassess;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/*1) Create an Employee Entity which contains following fields
   1.Name
   2.Id
   3.Age
4.Location*/
@Entity
public class Employee {
    @Id
    private int id;
    private String name;
    private int age;
    private String location;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}

