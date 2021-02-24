package com.carona.controller;

import com.carona.dto.UserDTO;
import com.carona.controller.valid.UserValidator;
import com.carona.entity.User;
import com.carona.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserValidator validator;

    @Autowired
    private UserService service;

    @PostMapping
    public ResponseEntity<User> create(@RequestBody UserDTO body, @NotNull BindingResult result) {
        validator.validate(body, result);

        Optional<User> entityOptional = service.save(body);

        return entityOptional.map((entity)->{
            return ResponseEntity.status(HttpStatus.CREATED).body(entity);

        }).orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }
}
