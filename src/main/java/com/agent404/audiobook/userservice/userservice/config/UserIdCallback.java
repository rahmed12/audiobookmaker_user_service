package com.agent404.audiobook.userservice.userservice.config;

import java.util.UUID;

import org.springframework.data.r2dbc.mapping.event.BeforeConvertCallback;
import org.springframework.data.relational.core.sql.SqlIdentifier;
import org.springframework.stereotype.Component;

import com.agent404.audiobook.userservice.userservice.model.User;

import reactor.core.publisher.Mono;

@Component
public class UserIdCallback implements BeforeConvertCallback<User>{

    @Override
    public Mono<User> onBeforeConvert(User user, SqlIdentifier table) {
        if (user.getId() == null) {
            user.setId(UUID.randomUUID());
        }
        return Mono.just(user);
    }

}
