package org.callboard.annotations;

import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface StringFormatValidation {

    String message() default "Wrong String Format";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
