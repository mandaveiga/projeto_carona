package com.carona.service;

import com.carona.dto.UserDTO;
import com.carona.entity.User;

import java.util.Optional;

public interface UserService {
    Optional<User> save(UserDTO body);
}
