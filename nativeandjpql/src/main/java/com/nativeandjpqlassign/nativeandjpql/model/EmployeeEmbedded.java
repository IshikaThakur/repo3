package com.nativeandjpqlassign.nativeandjpql.model;


import javax.persistence.*;

@Entity
    @Table(name="embedded")
    public class EmployeeEmbedded {
        @Id
        int id;
        String firstname;
        String lastname;
        int age;
        @Embedded
        Salary salary;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public Salary getSalary() {
            return salary;
        }

        public void setSalary(Salary salary) {
            this.salary = salary;
        }
    }

