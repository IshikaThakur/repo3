package com.springRestful2.SpringRestful2.Controller;


import com.springRestful2.SpringRestful2.ModelClasses.EmployeesV1;
import com.springRestful2.SpringRestful2.ModelClasses.EmployeesV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/* Ques10. Create 2 API for showing user details. The first api should return only basic details of the user and the other API should return more/enhanced details of the user,
           Now apply versioning using the following methods:
           2.Request Parameter versioning
           3. URI versioning
           4.Customer Header Versioning
 */


@RestController
public class EmployeesContoller {
    //using URI versioning
    @GetMapping("/Employees/v1")
    public EmployeesV1 getEmployeesV1(){
        return new EmployeesV1(001, "Ishika", "TTN");
    }

    //using URI versioning
    @GetMapping("/Employees/v2")
    public EmployeesV2 getEmployeesV2(){
        return new EmployeesV2(001, "Ishika","ishika.thakur@tothenew.com",  "TTN");
    }

    //using Request versioning
    @GetMapping(value = "/Employees/param", params="version=1")
    public EmployeesV1 paramV1(){
        return new EmployeesV1(001, "Ishika", "ishika.thakur@tothenew.com");
    }

    //using Request versioning
    @GetMapping(value = "/Employees/param", params = "version=2")
    public EmployeesV2 paramV2(){
        return new EmployeesV2(001, "Ishika","ishika.thakur@tothenew.com",  "ttn");
    }

    //using Header versioning
    @GetMapping(value = "/Employees/header", headers="hver=1")
    public EmployeesV1 headerV1(){
        return new EmployeesV1(001, "Ishika", "ttn");
    }

    //using Header versioning
    @GetMapping(value = "/Employees/header", headers = "hver=2")
    public EmployeesV2 headerV2(){
        return new EmployeesV2(001, "Ishika","ishika.thakur@tothenew.com",  "ttn");
    }

    //using Producer versioning
    @GetMapping(value = "/Employees/param", produces="application/v1+json")
    public EmployeesV1 mimeV1(){
        return new EmployeesV1(001, "Ishika", "ttn");
    }

    //using Header versioning
    @GetMapping(value = "/Employees/param", produces = "application/v2+json")
    public EmployeesV2 mimeV2(){
        return new EmployeesV2(001, "Ishika","ishika.thakur@tothenew.com",  "ttn");
    }
}
