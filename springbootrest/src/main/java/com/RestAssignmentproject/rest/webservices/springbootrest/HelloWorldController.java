package com.RestAssignmentproject.rest.webservices.springbootrest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

//Ques1. Create a simple REST ful service in Spring Boot which returns the Response "Welcome to spring boot".


@RestController
public class HelloWorldController{
    @GetMapping("SpringBoot")
    public String HelloWorld(){
        return "Welcome to spring boot";
    }

    @GetMapping("hello-world-bean")
    public HelloWorldBean helloWorldBean(){

        return new HelloWorldBean("Hello World");
    }
    @GetMapping(path = "hello-world/path-variable/{name}")
    public HelloWorldBean helloWorldPathVariable(@PathVariable String name){
        return new HelloWorldBean(String.format("Hello World, %s",name));
    }
}