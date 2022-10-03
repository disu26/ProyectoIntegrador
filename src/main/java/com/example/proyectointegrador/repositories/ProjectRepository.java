package com.example.proyectointegrador.repositories;

import com.example.proyectointegrador.collections.Project;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends ReactiveCrudRepository<Project, String> {
}
