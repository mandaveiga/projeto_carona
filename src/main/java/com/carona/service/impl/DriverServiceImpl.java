package com.carona.service.impl;

import com.carona.dto.DriverDTO;
import com.carona.entity.Driver;
import com.carona.entity.User;
import com.carona.error.BadResourceExcepion;
import com.carona.repository.DriverRepository;
import com.carona.service.DriverService;
import com.carona.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    private UserService userService;

    @Autowired
    private DriverRepository repository;


    @Override
    public Optional<Driver> save(DriverDTO body){

        Optional<User> userOptional = userService.findById(body.getUserId());

        return Optional.of((Driver) userOptional
                .map((user) -> {

                    Optional<Driver> driverOptional = repository.findByUserId(body.getUserId());

                    return driverOptional.map((driver -> {
                        throw new BadResourceExcepion("Driver already registered: " + user.getEmail());

                    })).orElse(repository.save(new Driver(user)));

                })
                .orElseThrow(() -> new BadResourceExcepion("user with id " +body.getUserId() + " not found")));
    }
}
