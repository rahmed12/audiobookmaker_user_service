package com.agent404.audiobook.userservice.userservice.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Table;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Table(name = "users")
@Data
public class User {

    @Id
    private UUID id;

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    private UserRole role = UserRole.USER;

    @CreatedDate
    private LocalDateTime createdAt;
    
    @LastModifiedDate
    private LocalDateTime updatedAt;

}
