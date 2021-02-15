package com.carona.service;

import com.carona.CaronaApplicationTests;
import com.carona.dto.DriverDTO;
import com.carona.dto.PassangerDTO;
import com.carona.dto.UserDTO;
import com.carona.entity.Driver;
import com.carona.entity.Passanger;
import com.carona.entity.User;
import com.carona.error.BadResourceExcepion;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

public class PassangerServiceTest extends CaronaApplicationTests {

    @Autowired
    PassangerService service;

    @Autowired
    UserService userService;

    @Test
    public void WhenToSaveThenReturnCreated(){
        String email = "amora@teste.com";

        UserDTO userDto = new UserDTO("ana", email);
        Optional<User> user = userService.save(userDto);

        PassangerDTO passangerDTO = new PassangerDTO(user.get().getId());
        Optional<Passanger> passanger = service.save(passangerDTO);

        assertThat(passanger.get().getUser().getEmail()).isEqualTo(email);
    }

    @Test
    public void givenUserInvalidWhenToSaveThenReturnBadResourceExcepion(){
        Long idInvalid =  1l;

        PassangerDTO passangerDTO = new PassangerDTO(idInvalid);

        try{
            Optional<Passanger> entity = service.save(passangerDTO);
        }catch (BadResourceExcepion e){
            assertThat("user with id " +idInvalid + " not found").isEqualTo(e.getMessage());
        }
    }
}
