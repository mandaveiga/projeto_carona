package com.carona.controller;

import com.carona.controller.valid.PassangerValidator;
import com.carona.dto.PassangerDTO;
import com.carona.entity.Passanger;
import com.carona.service.PassangerService;
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
@RequestMapping("/passangers")
public class PassangerController {

    @Autowired
    private PassangerValidator validator;

    @Autowired
    private PassangerService service;

    @PostMapping
    public ResponseEntity<Passanger> create(@RequestBody PassangerDTO body, @NotNull BindingResult result){
        validator.validate(body, result);

        Optional<Passanger> entity = service.save(body);

        if(!entity.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(entity.get());
    }
}
