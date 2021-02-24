package com.carona.service.impl;

import com.carona.error.BadResourceExcepion;
import com.carona.dto.UserDTO;
import com.carona.entity.User;
import com.carona.repository.UserRepository;
import com.carona.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public Optional<User> save(UserDTO body) {
        Optional<User> userOptional = userRepository.findByEmail(body.getEmail());

        return Optional.of((User) userOptional.map((user -> {
            throw new BadResourceExcepion("User already registered: " + body.getEmail());

        })).orElse(userRepository.save(new User(body.getName(), body.getEmail()))));
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
}
