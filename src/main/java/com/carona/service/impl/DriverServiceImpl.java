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
    UserService userService;

    @Autowired
    DriverRepository driverRepository;

    @Override
    public Optional<Driver> save(DriverDTO body) {

        Optional<User> user = userService.findById(body.getUserId());

        if(!user.isPresent()){
            throw new BadResourceExcepion("user with id " +body.getUserId() + " not found");
        }

        Driver driver = driverRepository.save(new Driver(user.get()));

        return Optional.ofNullable(driver);
    }
}
