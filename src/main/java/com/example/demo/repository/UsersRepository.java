package com.example.demo.repository;

import com.example.demo.model.Users;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface UsersRepository extends ReactiveMongoRepository<Users,String> {

    Flux<Users> findByUsernameAndDeleteFalse(String username);

    Mono<Users> findByIdAndDeleteIsFalse(String id);


}
