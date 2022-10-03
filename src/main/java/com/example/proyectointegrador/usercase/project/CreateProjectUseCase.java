package com.example.proyectointegrador.usercase.project;

import com.example.proyectointegrador.collections.Project;
import com.example.proyectointegrador.mapper.ProjectMapper;
import com.example.proyectointegrador.model.ProjectDTO;
import com.example.proyectointegrador.repositories.ProjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class CreateProjectUseCase implements SaveProject {

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    public CreateProjectUseCase(ProjectRepository projectRepository, ProjectMapper projectMapper) {
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
    }

    public Mono<String> apply(ProjectDTO projectDTO){
        if (projectDTO.getEndingDate() == null) return guardar(projectDTO);
        if (projectDTO.getStartDate().isBefore(projectDTO.getEndingDate()) || projectDTO.getStartDate().isEqual(projectDTO.getEndingDate())) return guardar(projectDTO);
        return Mono.error(new IllegalArgumentException("La fecha de inicio debe ser anterior o igual a la fecha de finalización"));
    }

    public Mono<String> guardar(ProjectDTO newProject) {
        return projectRepository.save(projectMapper.mapperToProject(null).apply(newProject))
                .map(Project::getId);
    }
}
