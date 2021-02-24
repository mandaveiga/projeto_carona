package com.carona.controller.valid;

import com.carona.dto.TravelDTO;
import com.carona.error.BadResourceExcepion;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class TravelValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public void validate(Object o, Errors errors) {
        TravelDTO travel = (TravelDTO) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "maxPassangers", "max passangers is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "value", "value is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "driverId", "driver is required");

        hasErros(errors);
    }

    private void hasErros(Errors errors) {
        if (errors.hasErrors()) {
            throw new BadResourceExcepion(errors.getAllErrors().get(0).getCode());
        }
    }

}
