package com.carona.service.impl;

import com.carona.dto.TravelDTO;
import com.carona.entity.Driver;
import com.carona.entity.Passanger;
import com.carona.entity.Travel;
import com.carona.error.BadResourceExcepion;
import com.carona.repository.DriverRepository;
import com.carona.repository.PassangerRepository;
import com.carona.repository.TravelRepository;
import com.carona.service.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TravelServiceimpl implements TravelService {

    @Autowired
    private TravelRepository repository;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private PassangerRepository passangerRepository;

    @Override
    public Optional<Travel> save(TravelDTO body) {

        Optional<Driver> driverOptional = driverRepository.findById(body.getDriverId());

        Optional<Driver> driver = driverOptional.map(d-> {
            return Optional.ofNullable(d);
        }).orElseThrow(()-> new BadResourceExcepion("driver with id " +body.getDriverId() + " not found"));

        body.getPassangers()
                .stream()
                .filter(id -> !passangerRepository.existsById(id))
                .forEach(filtered -> {
                    throw new BadResourceExcepion("passanger with id " + filtered + " not found");
                });

        List<Passanger> passangers = (List<Passanger>) passangerRepository.findAllById(body.getPassangers());

        return Optional.ofNullable(repository.save(new Travel(body.getValue(), true, body.getMaxPassangers(), passangers, driver.get())));
    }
}
