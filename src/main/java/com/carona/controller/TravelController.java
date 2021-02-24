package com.carona.controller;

import com.carona.controller.valid.TravelValidator;
import com.carona.dto.TravelDTO;
import com.carona.entity.Travel;
import com.carona.service.TravelService;
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
@RequestMapping("/travels")
public class TravelController {

    @Autowired
    private TravelValidator validator;

    @Autowired
    private TravelService service;

    @PostMapping
    public ResponseEntity<Travel> create(@RequestBody TravelDTO body, @NotNull BindingResult result){
        validator.validate(body, result);

        Optional<Travel> entityOptional = service.save(body);

        return entityOptional.map((entity)->{
            return ResponseEntity.status(HttpStatus.CREATED).body(entity);

        }).orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

}
