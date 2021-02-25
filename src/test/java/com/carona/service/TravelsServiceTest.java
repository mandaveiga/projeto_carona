package com.carona.service;

import com.carona.CaronaApplicationTests;
import com.carona.dto.*;
import com.carona.entity.*;
import com.carona.error.BadResourceExcepion;
import com.carona.repository.TravelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class TravelsServiceTest extends CaronaApplicationTests {

    @Autowired
    private TravelService service;

    @Autowired
    private PassangerService passangerService;

    @Autowired
    private DriverService driverService;

    @Autowired
    private UserService userService;

    private List<Long> passangers;

    private Optional<Driver> driver;

    @BeforeEach
    void init(){
        userService.save(new UserDTO("ana", "ana@teste.com"));
        userService.save(new UserDTO("matheus", "matheus@teste.com"));
        userService.save(new UserDTO("caio", "caio@teste.com"));
        userService.save(new UserDTO("joao", "joao@teste.com"));

        Optional<Passanger> passanger1 = passangerService.save(new PassangerDTO(1L));
        Optional<Passanger> passanger2 = passangerService.save(new PassangerDTO(3L));

        this.driver = driverService.save(new DriverDTO(2L));

        this.passangers = new ArrayList<>();
        passangers.add(passanger1.get().getId());
        passangers.add(passanger2.get().getId());
    }

    @Test
    public void WhenToSaveThenReturnTravel(){
        TravelDTO travelDTO = new TravelDTO(17L, 3, driver.get().getId(), passangers);
        Optional<Travel> entity = service.save(travelDTO);

        assertThat(entity.get().getDriver().getId()).isEqualTo(travelDTO.getDriverId());
    }

    @Test
    public void givenPassangerInvalidWhenToSaveThenReturnException(){
        Long idInvalid = 100L;

        passangers = new ArrayList<>();
        passangers.add(idInvalid);

        try{

            TravelDTO travelDTO = new TravelDTO(17L, 3, driver.get().getId(),passangers);
            Optional<Travel> entity = service.save(travelDTO);

        }catch (BadResourceExcepion e){
            assertThat("passanger with id "+ idInvalid +" not found").isEqualTo(e.getMessage());
        }
    }

    @Test
    public void givenDriverInvalidWhenToSaveThenReturnException(){
        Long driverInvalid = 100L;

        try{
            TravelDTO travelDTO = new TravelDTO(17L, 3, driverInvalid,passangers);
            Optional<Travel> entity = service.save(travelDTO);

        }catch (BadResourceExcepion e){
            assertThat("driver with id "+ driverInvalid +" not found").isEqualTo(e.getMessage());
        }
    }

    @Test
    public void givenTravelIdWhenClosedThenClosed(){
        Optional<Travel> travel = service.save(new TravelDTO(17L, 3, driver.get().getId(), passangers));

        Long idBody = travel.get().getId();

        Optional<Travel> entity = service.closed(idBody);

        assertThat(entity.get().isOpen()).isEqualTo(false);
    }
}
