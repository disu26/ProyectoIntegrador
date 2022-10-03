package com.example.proyectointegrador.repositories;

import com.example.proyectointegrador.collections.Risk;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface RiskRepository extends ReactiveCrudRepository<Risk, String> {
    Flux<Risk> findByUserId();
    Mono<Risk> findByProjectId(String id);
    Flux<Risk> findAllByProjectId(String id);
}
