package com.carona.controller;

import com.carona.dto.UserDTO;
import com.carona.controller.valid.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserValidator validator;

    @PostMapping
    @ResponseBody
    public ResponseEntity<Object> create(@RequestBody UserDTO body, @NotNull BindingResult result) {
        validator.validate(body, result);

        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(result.getAllErrors().get(0).getCode());
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
