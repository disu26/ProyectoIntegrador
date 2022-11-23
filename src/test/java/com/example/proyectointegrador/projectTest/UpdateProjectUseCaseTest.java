package com.example.proyectointegrador.projectTest;

import com.example.proyectointegrador.collections.Project;
import com.example.proyectointegrador.mapper.ProjectMapper;
import com.example.proyectointegrador.repositories.ProjectRepository;
import com.example.proyectointegrador.usercase.project.UpdateProjectUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@SpringBootTest
public final class UpdateProjectUseCaseTest {
    @Mock
    ProjectRepository projectRepository;


    UpdateProjectUseCase updateProjectUseCase;

    ProjectMapper projectMapper;

    @BeforeEach
    public void setup(){
        projectMapper = new ProjectMapper();
        updateProjectUseCase = new UpdateProjectUseCase(projectRepository, projectMapper);
    }

    @Test
    void updateProjectUseCaseTest(){
        var project = new Project();
        project.setId("1");
        project.setName("proyecto 1");
        project.setStartDate(LocalDate.now());
        project.setEndingDate(LocalDate.now());
        project.setDescription("Descripcion");
        project.setEmails(List.of("correo@gmail.com"));
        project.setLabels(List.of("label1", "label2"));
        project.setStatus("Creado");

        var projectReturn = new Project();
        projectReturn.setId("1");
        projectReturn.setName("proyecto 1");
        projectReturn.setStartDate(LocalDate.now());
        projectReturn.setEndingDate(LocalDate.of(2022,7,4));
        projectReturn.setDescription("Descripcion modificada");
        projectReturn.setEmails(List.of("correo@gmail.com","correo1@gmail.com"));
        projectReturn.setLabels(List.of("label1", "label2", "label3"));
        projectReturn.setStatus("Creado");

        var projectDTO = projectMapper.entityToProjectDTO().apply(project);

        when(projectRepository.findById("1")).thenReturn(Mono.just(project));
        when(projectRepository.save(project)).thenReturn(Mono.just(projectReturn));

        StepVerifier.create(updateProjectUseCase.apply(projectDTO))
                .expectNext("1")
                .verifyComplete();

        verify(projectRepository).save(project);


    }
}
