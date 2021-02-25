package com.carona.service;

import com.carona.dto.TravelDTO;
import com.carona.entity.Travel;

import java.util.Optional;

public interface TravelService {

    Optional<Travel> save(TravelDTO body);

    Optional<Travel> closed(Long id);
}
