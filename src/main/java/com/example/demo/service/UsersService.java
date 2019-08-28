package com.example.demo.service;

import com.example.demo.model.Users;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UsersService {

    Flux<Users> findAll();

    Mono<Users> createUsers(Users users);

    Mono<Users> findOne(String id);

    Mono<Users> updateUsers(Users users, String id);

    Flux<Users> findByUsername(String title);

    Mono<Boolean> delete(String id);
}
