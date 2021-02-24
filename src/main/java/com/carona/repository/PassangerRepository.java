package com.carona.repository;

import com.carona.entity.Passanger;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PassangerRepository extends CrudRepository<Passanger, Long> {
    Optional<Passanger> findByUserId(Long userId);

}
