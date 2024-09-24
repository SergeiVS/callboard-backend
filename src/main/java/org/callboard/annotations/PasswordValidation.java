package org.callboard.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.ArrayList;
import java.util.List;

public class PasswordValidation implements ConstraintValidator<StringFormatValidation, String> {
    @Override
    public void initialize(StringFormatValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {


        if (password == null) {
            addConstraintViolation(context, "Password could not be empty");
            return false;
        }

        if (password.length() < 8 || password.length() > 16) {
            addConstraintViolation(context, "Password must be between 8 and 16 characters");
            return false;
        }

        if (!password.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%&-_]){8,}$")) {
            addConstraintViolation(context, "Password should contain at leas one letter in upper- and lowercase, number and special character");
            return false;
        }


        return true;
    }

    static void addConstraintViolation(ConstraintValidatorContext context, String message) {
        context
                .buildConstraintViolationWithTemplate(message)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
    }
}
