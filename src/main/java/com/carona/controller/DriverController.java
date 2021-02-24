package com.carona.controller;

import com.carona.controller.valid.DriverValidator;
import com.carona.dto.DriverDTO;
import com.carona.entity.Driver;
import com.carona.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@RestController
@RequestMapping("/drivers")
public class DriverController {

    @Autowired
    private DriverValidator validator;

    @Autowired
    private DriverService driverService;

    @PostMapping
    public ResponseEntity<Driver> create(@RequestBody DriverDTO body, @NotNull BindingResult result){
        validator.validate(body, result);

        Optional<Driver> entityOptional = driverService.save(body);

        return entityOptional.map((entity) -> {
            return ResponseEntity.status(HttpStatus.CREATED).body(entity);

        }).orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }
}
