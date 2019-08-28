package com.example.demo.controller;

import com.example.demo.service.UsersService;
import com.example.demo.model.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class UsersController {

    @Autowired
    private UsersService usersService;

    Logger log = LoggerFactory.getLogger(UsersController.class);

    /**新增*/
    @PostMapping(value = "/add")
    public Mono<Users> add(@RequestBody Users users){
        log.debug("create Users with users : {}", users);
        return usersService.createUsers(users);
    }

    /**取全部*/
    @GetMapping(value = "/findAll")
    public Flux<Users> findAll() {
        log.debug("findAll Users");
        return usersService.findAll();
    }

    /**依username取user*/
    @GetMapping("/find")
    public Flux<Users> findByUsername(@RequestParam String username) {
        log.debug("findByTitle Users with username : {}", username);
        return usersService.findByUsername(username);
    }

    /**依id取user*/
    @GetMapping("/{id}")
    public Mono<Users> findOne(@PathVariable String id) {
        log.debug("findOne Users with id : {}", id);
        return usersService.findOne(id);
    }

    /**更新user*/
    @PutMapping("/{id}")
    public Mono<Users> updateUsers(@RequestBody Users users, @PathVariable String id) {
        log.debug("updateUsers Users with id : {} and Users : {}", id, users);
        return usersService.updateUsers(users, id);
    }

    /**刪除user*/
    @DeleteMapping("/{id}")
    public Mono<Boolean> delete(@PathVariable String id) {
        log.debug("delete Users with id : {}", id);
        return usersService.delete(id);
    }
}