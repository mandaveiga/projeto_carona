package com.carona.repository;

import com.carona.entity.Driver;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DriverRepository extends CrudRepository<Driver, Long> {
    Optional<Driver> findByUserId(Long userId);
}
