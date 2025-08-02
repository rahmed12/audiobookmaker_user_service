package com.agent404.audiobook.userservice.userservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.agent404.audiobook.userservice.userservice.dto.CreateUserRequest;
import com.agent404.audiobook.userservice.userservice.dto.UserResponse;
import com.agent404.audiobook.userservice.userservice.service.impl.UserServiceImpl;

import jakarta.validation.Valid;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import reactor.core.publisher.Mono;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/v1/api/users")
public class UserController {

    // TODO: Make this config after getting it to work
    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping("/{userId}")
    public Mono<UserResponse> getUserProfile(@PathVariable UUID userId) {
        return userServiceImpl.getUserProfile(userId);
    }


    @PostMapping("/create")
    public Mono<UserResponse> createUser(@Valid @RequestBody CreateUserRequest createUserRequest ) {
        // TODO dont return password from userResponse
        return userServiceImpl.create(createUserRequest);
    }

    @GetMapping("/{userId}/validate")
    public Mono<Boolean> isUserValid(@PathVariable UUID userId) {
        return userServiceImpl.existsByUserId(userId);
    }

}
