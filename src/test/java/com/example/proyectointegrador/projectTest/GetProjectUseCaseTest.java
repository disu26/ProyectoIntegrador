package com.example.proyectointegrador.projectTest;

import com.example.proyectointegrador.collections.Project;
import com.example.proyectointegrador.collections.Risk;
import com.example.proyectointegrador.mapper.ProjectMapper;
import com.example.proyectointegrador.mapper.RiskMapper;
import com.example.proyectointegrador.repositories.ProjectRepository;
import com.example.proyectointegrador.repositories.RiskRepository;
import com.example.proyectointegrador.usercase.project.GetProjectUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
final class GetProjectUseCaseTest {

    GetProjectUseCase getProjectUseCase;

    @Mock
    ProjectRepository projectRepository;

    @Mock
    RiskRepository riskRepository;

    ProjectMapper projectMapper = new ProjectMapper();
    RiskMapper riskMapper = new RiskMapper();

    @BeforeEach
    void setup() {
        getProjectUseCase = new GetProjectUseCase(projectRepository, riskRepository, projectMapper, riskMapper);
    }

    @Test
    void setGetProjectUseCaseTest() {
        var risk = new Risk();
        risk.setId("1");
        risk.setProjectId("1");
        risk.setName("Riesgo 1");
        risk.setEndedDate(LocalDate.now());
        risk.setLabels(List.of("label1", "label2"));
        risk.setDescription("Descripcion");
        risk.setRiskState("Abierto");
        risk.setCategory("Categoria");
        risk.setRiskType("Riesgo de producto");
        risk.setDetailsRiskType("Detalle riesgo");
        risk.setProbability(5);
        risk.setImpactValue(4);
        risk.setMitigationPlan("Plan de mitigación");
        risk.setResponsibleMitigationMails(List.of("email1", "email2"));
        risk.setContingencyPlan("Plan de contingencia");
        risk.setResponsibleContingencyMails(List.of("email1", "email2"));

        var riskDTO = riskMapper.RiskToRiskDTO().apply(risk);

        var proyecto = new Project();
        proyecto.setId("1");
        proyecto.setName("ProjectTest");
        proyecto.setEndingDate(LocalDate.of(2022, 7, 4));
        proyecto.setDescription("Descripcion del proyecto");
        proyecto.setStatus("Creado");
        proyecto.setLabels(List.of("label1", "label2"));
        proyecto.setEmails(List.of("correo@gmail.com"));

        when(projectRepository.findById(proyecto.getId())).thenReturn(Mono.just(proyecto));
        when(riskRepository.findAllByProjectId(proyecto.getId())).thenReturn(Flux.just(risk));

        StepVerifier.create(getProjectUseCase.apply(proyecto.getId()))
                .expectNextMatches(projectDTO -> {
                    assert projectDTO.getId().equals(proyecto.getId());
                    assert projectDTO.getName().equals(proyecto.getName());
                    assert projectDTO.getEndingDate().equals(proyecto.getEndingDate());
                    assert projectDTO.getDescription().equals(proyecto.getDescription());
                    assert projectDTO.getStatus().equals(proyecto.getStatus());
                    assert projectDTO.getLabels().equals(proyecto.getLabels());
                    assert projectDTO.getEmails().equals(proyecto.getEmails());
//                    assert projectDTO.getRisks().equals(List.of(riskDTO));
                    return true;
                })
                .verifyComplete();

        verify(projectRepository).findById(proyecto.getId());
        verify(riskRepository).findAllByProjectId(proyecto.getId());

    }
}
