package com.example.demo.service.imp;

import com.example.demo.repository.UsersRepository;
import com.example.demo.model.Users;
import com.example.demo.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UsersServiceImp implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public Mono<Users> createUsers(@RequestBody Users users) {
        return usersRepository.insert(users);
    }

    @Override
    public Flux<Users> findAll() {
        Flux<Users> flux = usersRepository.findAll();
        flux.subscribe(System.out::println);
        return flux;
    }

    @Override
    public Mono<Users> findOne(@PathVariable String id) {
        return usersRepository.findByIdAndDeleteIsFalse(id).
                switchIfEmpty(Mono.error(new Exception("No Users found with Id: " + id)));
    }

    @Override
    public Flux<Users> findByUsername(@RequestParam String username) {
        return usersRepository.findByUsernameAndDeleteFalse(username)
                .switchIfEmpty(Mono.error(new Exception("No User found with title Containing: " + username)));
    }

    @Override
    public Mono<Users> updateUsers(@RequestBody Users users, @PathVariable String id) {
        return findOne(id).doOnSuccess(findUsers -> {
            findUsers.setUsername(users.getUsername());
            findUsers.setPassword(users.getPassword());
            usersRepository.save(findUsers).subscribe();
        });
    }

    @Override
    public Mono<Boolean> delete(@PathVariable String id) {
        return findOne(id).doOnSuccess(users -> {
            users.setDelete(true);
            usersRepository.save(users).subscribe();
//            usersRepository.delete(users).subscribe(System.out :: println);
        }).flatMap(users -> Mono.just(Boolean.TRUE));
    }

}
