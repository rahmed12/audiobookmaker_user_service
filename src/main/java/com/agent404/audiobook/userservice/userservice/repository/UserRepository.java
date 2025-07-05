package com.agent404.audiobook.userservice.userservice.repository;

import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.agent404.audiobook.userservice.userservice.model.User;

import reactor.core.publisher.Mono;
 
@Repository
public interface UserRepository extends ReactiveCrudRepository<User, UUID>{

     Mono<Boolean> existsByEmail(String email);
}

