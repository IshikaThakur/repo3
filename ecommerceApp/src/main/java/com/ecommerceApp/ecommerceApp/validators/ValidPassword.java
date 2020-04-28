package com.ecommerceApp.ecommerceApp.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailValidator.class)
public @interface ValidPassword {

    String message()  default "Invalid Password";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default{};
}

