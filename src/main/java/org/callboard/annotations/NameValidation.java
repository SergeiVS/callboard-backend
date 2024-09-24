package org.callboard.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import static org.callboard.annotations.PasswordValidation.addConstraintViolation;

public class NameValidation implements ConstraintValidator<StringFormatValidation, String> {
    @Override
    public void initialize(StringFormatValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {


        if (name == null) {
            addConstraintViolation(context, "Name could not be empty");
            return false;
        }

        if (name.length() < 3 || name.length() > 25) {
            addConstraintViolation(context, "Name length must be between 3 and 25 characters");
            return false;
        }

        if (!name.matches("[A-Za-z0-9\\s]{3,25}")) {

            addConstraintViolation(context, "Name should contain letters and numbers only");
            return false;
        }

        return true;
    }


}
