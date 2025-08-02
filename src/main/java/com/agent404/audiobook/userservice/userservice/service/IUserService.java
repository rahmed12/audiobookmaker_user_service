package com.agent404.audiobook.userservice.userservice.service;

import java.util.UUID;

import com.agent404.audiobook.userservice.userservice.dto.CreateUserRequest;
import com.agent404.audiobook.userservice.userservice.dto.UserResponse;

import reactor.core.publisher.Mono;

public interface IUserService {
    Mono<UserResponse> create(CreateUserRequest createUserRequest);
    Mono<UserResponse> getUserProfile(UUID userId);
    Mono<Boolean> existsByUserId(UUID userId);
}
