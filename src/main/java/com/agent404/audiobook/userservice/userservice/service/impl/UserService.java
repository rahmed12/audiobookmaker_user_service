package com.agent404.audiobook.userservice.userservice.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agent404.audiobook.userservice.userservice.dto.CreateUserRequest;
import com.agent404.audiobook.userservice.userservice.dto.UserResponse;
import com.agent404.audiobook.userservice.userservice.exception.ResourceNotFoundException;
import com.agent404.audiobook.userservice.userservice.model.User;
import com.agent404.audiobook.userservice.userservice.repository.UserRepository;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Mono<UserResponse> create(CreateUserRequest createUserRequest) {

        // return Mono.fromCallable(() -> {
        //     if (userRepository.existsByEmail(createUserRequest.getEmail())){
        //         throw new RuntimeException("Email already exist: " + createUserRequest.getEmail());
        //     }

        //     User user = new User();
        //     user.setEmail(createUserRequest.getEmail());
        //     user.setPassword(createUserRequest.getPassword());

        //     User savedUser  = userRepository.save(user);

        //     UserResponse userResponse = new UserResponse();
        //     userResponse.setId(savedUser.getId());
        //     userResponse.setPassword(savedUser.getPassword());
        //     userResponse.setEmail(savedUser.getEmail());
        //     userResponse.setCreatedAt(savedUser.getCreatedAt());
        //     userResponse.setUpdatedAt(savedUser.getUpdatedAt());

        //     return userResponse;
        // });
        return Mono.empty();

    }

    public Mono<UserResponse> getUserProfile(UUID userId) {
        return userRepository.findById(userId)
        .switchIfEmpty(Mono.error(new ResourceNotFoundException("User Not Found: " + userId)))
        .map(user -> {
            UserResponse userResponse = new UserResponse();
            userResponse.setId(user.getId());
            userResponse.setPassword(user.getPassword());
            userResponse.setEmail(user.getEmail());
            userResponse.setCreatedAt(user.getCreatedAt());
            userResponse.setUpdatedAt(user.getUpdatedAt());     
            
            return userResponse;
        });
        
        // return Mono.fromCallable(() -> {
        //     User user = userRepository.findById(userId)
        //     .orElseThrow(() -> new ResourceNotFoundException("User Not Found: " + userId));
            
        //     UserResponse userResponse = new UserResponse();
        //     userResponse.setId(user.getId());
        //     userResponse.setPassword(user.getPassword());
        //     userResponse.setEmail(user.getEmail());
        //     userResponse.setCreatedAt(user.getCreatedAt());
        //     userResponse.setUpdatedAt(user.getUpdatedAt());     
            
        //     return userResponse;

        // }).subscribeOn(Schedulers.boundedElastic());
    }

}
