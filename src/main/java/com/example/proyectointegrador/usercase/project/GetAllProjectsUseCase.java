package com.example.proyectointegrador.usercase.project;

import com.example.proyectointegrador.mapper.ProjectMapper;
import com.example.proyectointegrador.model.ProjectDTO;
import com.example.proyectointegrador.repositories.ProjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;

@Service
@Validated
public class GetAllProjectsUseCase implements Supplier<Flux<ProjectDTO>> {

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    public GetAllProjectsUseCase(ProjectRepository projectRepository, ProjectMapper projectMapper) {
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
    }

    @Override
    public Flux<ProjectDTO> get() {
        return projectRepository.findAll().map(projectMapper.entityToProjectDTO());
    }
}
