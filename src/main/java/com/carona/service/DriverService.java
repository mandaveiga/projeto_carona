package com.carona.service;

import com.carona.dto.DriverDTO;
import com.carona.entity.Driver;

import java.util.Optional;

public interface DriverService {
    Optional<Driver> save(DriverDTO body);
}
