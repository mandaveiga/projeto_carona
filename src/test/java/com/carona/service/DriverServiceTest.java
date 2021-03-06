package com.carona.service;

import com.carona.CaronaApplicationTests;
import com.carona.dto.DriverDTO;
import com.carona.dto.UserDTO;
import com.carona.entity.Driver;
import com.carona.entity.User;
import com.carona.error.BadResourceExcepion;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class DriverServiceTest extends CaronaApplicationTests {

    @Autowired
    DriverService driverService;

    @Autowired
    UserService userService;

    @Test
    public void givenUserWhenToSaveThenReturnDriver(){
        String email = "amora@teste.com";

        UserDTO userDto = new UserDTO("ana", email);
        Optional<User> user = userService.save(userDto);

        DriverDTO driverDto = new DriverDTO(user.get().getId());
        Optional<Driver> driver = driverService.save(driverDto);

        assertThat(driver.get().getUser().getEmail()).isEqualTo(email);
    }

    @Test
    public void givenUserInvalidWhenToSaveThenReturnBadResourceExcepion(){
        Long idInvalid =  1l;

        DriverDTO driverDto = new DriverDTO(idInvalid);

        try{
            Optional<Driver> entity = driverService.save(driverDto);
        }catch (BadResourceExcepion e){
            assertThat("user with id " +idInvalid + " not found").isEqualTo(e.getMessage());
        }
    }
}
