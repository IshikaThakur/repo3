package com.SpringAssignment.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class BootCampApplication {

    public static void main(String[] args) {
//        SpringApplication.run(BootCampApplication.class, args);
        ApplicationContext applicationContext=SpringApplication.run(BootCampApplication.class ,args);
        Trainee emp=applicationContext.getBean(Trainee.class);
        emp.setId(210);
        emp.setName("Ishika");
        System.out.println("=================================");
        System.out.println(emp.toString());
        emp.showCompetency();               //By default it will show competency with @Primary i.e JVM


   /*     Trainee emp1=new Trainee();
        emp1.setId(210);
        emp1.setName("Ishika");
        System.out.println("=================================");
        System.out.println(emp1.toString());
        emp1.setCompetency1();
    }
*/
    }
}