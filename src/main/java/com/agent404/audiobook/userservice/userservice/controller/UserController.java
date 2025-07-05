package com.agent404.audiobook.userservice.userservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.agent404.audiobook.userservice.userservice.dto.CreateUserRequest;
import com.agent404.audiobook.userservice.userservice.dto.UserResponse;
import com.agent404.audiobook.userservice.userservice.exception.ResourceNotFoundException;
import com.agent404.audiobook.userservice.userservice.service.impl.UserService;

import jakarta.validation.Valid;

import java.security.Principal;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import reactor.core.publisher.Mono;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/v1/api/users")
public class UserController {

    // TODO: Make this config after getting it to work
    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    public Mono<UserResponse> getUserProfile(@PathVariable UUID userId) {

        return userService.getUserProfile(userId);

        // return userService.getUserProfile(userId)
        //     .map(userResponse -> ResponseEntity.ok().body(userResponse))
        //     .onErrorResume(e -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()));
        // return userService.getUserProfile(userId)
        //     .<ResponseEntity<?>>map(userResponse -> ResponseEntity.ok().body(userResponse))
        //     .onErrorResume(e -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage())));

    }


    @PostMapping("/create")
    public Mono<ResponseEntity<UserResponse>> createUser(@Valid @RequestBody CreateUserRequest createUserRequest ) {

        return userService.create(createUserRequest)
            // TODO dont return password from userResponse
            .map(userResponse -> ResponseEntity.ok().body(userResponse)) 
            .onErrorResume(e -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()));
    }

}
