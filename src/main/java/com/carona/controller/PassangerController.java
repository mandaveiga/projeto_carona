package com.carona.controller;

import com.carona.controller.valid.PassangerValidator;
import com.carona.dto.PassangerDTO;
import com.carona.entity.Passanger;
import com.carona.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/passangers")
public class PassangerController {

    @Autowired
    private PassangerValidator validator;

    @PostMapping
    public ResponseEntity<Passanger> create(@RequestBody PassangerDTO body, @NotNull BindingResult result){
        validator.validate(body, result);

        Passanger passanger = new Passanger(new User(body.getUserId(),"ana", "ana@teste.com"));

        return ResponseEntity.status(HttpStatus.CREATED).body(passanger);

    }
}
