package com.carona.controller.valid;

import com.carona.error.BadResourceExcepion;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class PassangerValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userId", "id user is required");

        hasErros(errors);
    }

    private void hasErros(Errors errors) {
        if (errors.hasErrors()) {
            throw new BadResourceExcepion(errors.getAllErrors().get(0).getCode());
        }
    }
}
