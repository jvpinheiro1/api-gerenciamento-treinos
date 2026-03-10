package com.jvpinheiro.api.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jvpinheiro.api.dto.CreateUserRequest;
import com.jvpinheiro.api.dto.UserResponse;
import com.jvpinheiro.api.model.User;
import com.jvpinheiro.api.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public UserResponse createUser(CreateUserRequest  request) {
        if(userRepository.findByEmail(request.getEmail()) != null) {
            throw new IllegalArgumentException("Email já cadastrado!");
        }

        User  newUser = new User();
        newUser.setName(request.getName());
        newUser.setEmail(request.getEmail());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(newUser);

        UserResponse response = new UserResponse();
        response.setId(newUser.getId());
        response.setName(newUser.getName());
        response.setEmail(newUser.getEmail());
        return response;
    }

}
