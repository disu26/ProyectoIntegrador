package com.example.proyectointegrador.usercase.project;

import com.example.proyectointegrador.repositories.ProjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Function;

@Service
@Validated
public class DeleteProjectUseCase implements Function<String, Mono<Void>> {

    private final ProjectRepository projectRepository;

    public DeleteProjectUseCase(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Mono<Void> apply(String id) {
        Objects.requireNonNull(id, "Id es requerido");

        return projectRepository.findById(id)
                .flatMap(project -> {
                    if (project.getStatus().equalsIgnoreCase("Creado")) {
                        return projectRepository.deleteById(id);
                    }
                    return Mono.error(new IllegalArgumentException("El proyecto no puede ser eliminado"));
                });
    }
}
