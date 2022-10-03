package com.example.proyectointegrador.repositories;

import com.example.proyectointegrador.collections.History;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository extends ReactiveCrudRepository<History, String> {
}
