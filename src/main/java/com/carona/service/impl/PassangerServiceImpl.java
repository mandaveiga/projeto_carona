package com.carona.service.impl;


import com.carona.dto.PassangerDTO;
import com.carona.entity.Driver;
import com.carona.entity.Passanger;
import com.carona.entity.User;
import com.carona.error.BadResourceExcepion;
import com.carona.repository.PassangerRepository;
import com.carona.service.PassangerService;
import com.carona.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PassangerServiceImpl implements PassangerService {

    @Autowired
    private UserService userService;

    @Autowired
    private PassangerRepository repository;

    @Override
    public Optional<Passanger> save(PassangerDTO body) {

        Optional<User> userOptional = userService.findById(body.getUserId());

        return Optional.of((Passanger) userOptional
                .map((user -> {

                    Optional<Passanger> passangerOptional = repository.findByUserId(body.getUserId());

                    return passangerOptional.map((passanger -> {
                        throw new BadResourceExcepion("Passanger already registered: " + user.getEmail());

                    }))
                    .orElse(repository.save(new Passanger(user)));

                }))
                .orElseThrow(()-> new BadResourceExcepion("user with id " +body.getUserId() + " not found")));
    }
}
