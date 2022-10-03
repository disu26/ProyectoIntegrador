package com.example.proyectointegrador.usercase.project;

import com.example.proyectointegrador.model.ProjectDTO;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@FunctionalInterface
public interface SaveProject {
    Mono<String> apply(@Valid ProjectDTO projectDTO);
}
