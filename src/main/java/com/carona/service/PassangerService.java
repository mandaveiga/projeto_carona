package com.carona.service;

import com.carona.dto.PassangerDTO;
import com.carona.entity.Passanger;

import java.util.Optional;

public interface PassangerService{

    Optional<Passanger> save(PassangerDTO body);
}
