package com.carona.service;

import com.carona.dto.DriverDto;
import com.carona.entity.Driver;

import java.util.Optional;

public interface DriverService {
    Optional<Driver> save(DriverDto body);
}
