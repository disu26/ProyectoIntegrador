package com.example.proyectointegrador.projectTest;

import com.example.proyectointegrador.collections.Project;
import com.example.proyectointegrador.mapper.ProjectMapper;
import com.example.proyectointegrador.repositories.ProjectRepository;
import com.example.proyectointegrador.usercase.project.GetAllProjectsUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@SpringBootTest
final class GetAllProjectsUseCaseTest {

    @Mock
    ProjectRepository projectRepository;

    GetAllProjectsUseCase getAllProjectsUseCase;

    @BeforeEach
    void setup() {
        ProjectMapper projectMapper = new ProjectMapper();
        getAllProjectsUseCase = new GetAllProjectsUseCase(projectRepository, projectMapper);
    }

    @Test
    void getAllProjectsTest(){
        var project = new Project();
        project.setId("1");
        project.setName("Project 1");
        project.setStartDate(LocalDate.now());
        project.setEndingDate(LocalDate.now());
        project.setLabels(List.of("label 1", "label 2"));
        project.setEmails(List.of("email 1", "email 2"));
        project.setDescription("description");
        project.setStatus("status");

        when(projectRepository.findAll()).thenReturn(Flux.just(project));

        StepVerifier.create(getAllProjectsUseCase.get())
                .expectNextMatches(projectDTO -> {
                    assert projectDTO.getId().equals("1");
                    assert projectDTO.getName().equals("Project 1");
                    assert projectDTO.getStartDate().equals(LocalDate.now());
                    assert projectDTO.getEndingDate().equals(LocalDate.now());
                    assert projectDTO.getLabels().equals(List.of("label 1", "label 2"));
                    assert projectDTO.getEmails().equals(List.of("email 1", "email 2"));
                    assert projectDTO.getDescription().equals("description");
                    assert projectDTO.getStatus().equals("status");
                    return true;
                })
                .verifyComplete();

        verify(projectRepository).findAll();

    }
}
