package com.vashchenko.micro.edu.menuservice.model.validation.annotation;

import com.vashchenko.micro.edu.menuservice.model.validation.validators.PriceValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PriceValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface MoneyType {
    String message() default "Price must be a number with two decimal places";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
