package com.agent404.audiobook.userservice.userservice.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agent404.audiobook.userservice.userservice.dto.CreateUserRequest;
import com.agent404.audiobook.userservice.userservice.dto.UserResponse;
import com.agent404.audiobook.userservice.userservice.exception.DuplicateEmailExceptoin;
import com.agent404.audiobook.userservice.userservice.exception.ResourceNotFoundException;
import com.agent404.audiobook.userservice.userservice.model.User;
import com.agent404.audiobook.userservice.userservice.model.UserRole;
import com.agent404.audiobook.userservice.userservice.repository.UserRepository;

import reactor.core.publisher.Mono;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Mono<UserResponse> create(CreateUserRequest createUserRequest) {

        return userRepository.existsByEmail(createUserRequest.getEmail())
        .flatMap(exists -> {
            if (exists) {
                return Mono.error(new DuplicateEmailExceptoin("Email Already exists: " + createUserRequest.getEmail()));
            }

            User user = new User();
            user.setEmail(createUserRequest.getEmail());
            user.setPassword(createUserRequest.getPassword());
            user.setRole(UserRole.USER); // optional

            return userRepository.save(user)
                .map(savedUser -> {
                    UserResponse userResponse = new UserResponse();
                    userResponse.setId(savedUser.getId());
                    userResponse.setEmail(savedUser.getEmail());
                    userResponse.setPassword(savedUser.getPassword());
                    userResponse.setCreatedAt(savedUser.getCreatedAt());
                    userResponse.setUpdatedAt(savedUser.getUpdatedAt());
                    return userResponse;
                });
        });
        
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
        
    }

}
