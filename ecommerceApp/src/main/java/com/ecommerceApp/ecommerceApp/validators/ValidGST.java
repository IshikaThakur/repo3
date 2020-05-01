package com.ecommerceApp.ecommerceApp.validators;

import javax.validation.Payload;

import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD,FIELD,ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = GSTValidator.class)
public @interface ValidGST {

    String message()  default "Invalid GST";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default{};
}