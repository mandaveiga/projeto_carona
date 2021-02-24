package com.carona.controller.valid;

import com.carona.dto.UserDTO;
import com.carona.error.BadResourceExcepion;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserDTO user = (UserDTO) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "Name is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "email is required");

        hasErros(errors);
    }

    private void hasErros(Errors errors) {
        if (errors.hasErrors()) {
            throw new BadResourceExcepion(errors.getAllErrors().get(0).getCode());
        }
    }

}
