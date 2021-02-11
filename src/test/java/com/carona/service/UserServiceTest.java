package com.carona.service;

import com.carona.CaronaApplicationTests;
import com.carona.error.BadResourceExcepion;
import com.carona.dto.UserDTO;
import com.carona.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UserServiceTest extends CaronaApplicationTests {

    @Autowired
    UserService service;

    @Test
    public void shouldReturnUse_whenToSave() {
        UserDTO userDTO = new UserDTO("ana", "ana@teste.com");

        Optional<User> entity = service.save(userDTO);

        assertThat(entity.get().getEmail()).isEqualTo("ana@teste.com");
        assertThat(entity.get().getName()).isEqualTo("ana");
    }

    @Test
    public void givenDuplicEmailThenShouldThrowBadResourceExcepion_whenToSave(){
        UserDTO userDTO = new UserDTO("ana", "ana@teste.com");

        try{

            Optional<User> entity = service.save(userDTO);

        }catch(BadResourceExcepion e){
            assertEquals("User already registered: " +userDTO.getEmail() , e.getMessage());
        }

    }
}