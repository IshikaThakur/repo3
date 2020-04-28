package com.ecommerceApp.ecommerceApp.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword,String> {
        private Pattern pattern;
        private Matcher matcher;

        private static final String Password_Pattern = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";

        @Override
public void initialize(ValidPassword constraintAnnotation) {

        }

@Override
public boolean isValid(String password, ConstraintValidatorContext context) {

        return (validatePassword(password));
        }
        private boolean validatePassword(String password) {
                pattern = Pattern.compile(Password_Pattern);
                matcher = pattern.matcher(password);
                return matcher.matches();
        }
        }

