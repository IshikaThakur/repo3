package com.SpringAssignment.spring;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
// Ques 5: Demonstrate how you will resolve ambiguity while autowiring bean (Hint : @Primary)
@Primary
@Component
public class JVM implements Competency {
    @Override
    public  void getCompetency()
    {
        System.out.println("This is JVM group");
    }
}
