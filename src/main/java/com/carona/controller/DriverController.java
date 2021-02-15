package com.carona.controller;

import com.carona.controller.valid.DriverValidator;
import com.carona.dto.DriverDto;
import com.carona.entity.Driver;
import com.carona.entity.User;
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
    public ResponseEntity<Object> create(@RequestBody DriverDto body, @NotNull BindingResult result){
        validator.validate(body, result);

        Optional<Driver> entity = driverService.save(body);

        if(!entity.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(entity);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(entity);
    }
}
