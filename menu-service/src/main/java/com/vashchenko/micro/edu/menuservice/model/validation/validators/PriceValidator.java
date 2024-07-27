package com.vashchenko.micro.edu.menuservice.model.validation.validators;

import com.vashchenko.micro.edu.menuservice.model.validation.annotation.MoneyType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PriceValidator implements ConstraintValidator<MoneyType, Double> {


    @Override
    public boolean isValid(Double value, ConstraintValidatorContext context) {
        return isHasTwoDecimalPlaces(value);
    }

    private boolean isHasTwoDecimalPlaces(double value) {
        String strValue = String.valueOf(value);
        if(strValue.length()-strValue.indexOf(".")>3){
            return false;
        }
        return true;
    }
}
