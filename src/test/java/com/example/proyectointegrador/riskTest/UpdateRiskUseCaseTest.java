package com.example.proyectointegrador.riskTest;

import com.example.proyectointegrador.collections.Risk;
import com.example.proyectointegrador.mapper.RiskMapper;
import com.example.proyectointegrador.repositories.HistoryRepository;
import com.example.proyectointegrador.repositories.RiskRepository;
import com.example.proyectointegrador.usercase.project.GetProjectUseCase;
import com.example.proyectointegrador.usercase.risk.UpdateRiskUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@SpringBootTest
public final class UpdateRiskUseCaseTest {

    @Mock
    RiskRepository riskRepository;

    UpdateRiskUseCase updateRiskUseCase;
    HistoryRepository historyRepository;
    GetProjectUseCase getProjectUseCase;

    RiskMapper riskMapper;

    @BeforeEach
    public void setup() {
        riskMapper = new RiskMapper();
        updateRiskUseCase = new UpdateRiskUseCase(riskMapper, riskRepository, historyRepository, getProjectUseCase);
    }

    @Test
    void updateRiskUseCaseTest() {
        var risk = new Risk();
        risk.setId("1");
        risk.setProjectId("123");
        risk.setName("Risk");
        risk.setUserId("321");
        risk.setEndedDate(LocalDate.of(2022, 06, 30));
        risk.setLabels(List.of("Label1", "Label2"));
        risk.setDescription("Descripcion1");
        risk.setRiskState("Abierto");
        risk.setAudience("Interna");
        risk.setCategory("Costo");
        risk.setRiskType("Riesgo de producto o calidad");
        risk.setDetailsRiskType("Detail1");
        risk.setProbability(3);
        risk.setImpactValue(3);
        risk.setMitigationPlan("Mitigation plan 1");
        risk.setResponsibleMitigationMails(List.of("user1@gmail.com", "user2@gmail.com"));
        risk.setContingencyPlan("Contingency 1");
        risk.setResponsibleContingencyMails(List.of("user1@gmail.com", "user2@gmail.com"));

        var riskReturn = new Risk();
        riskReturn.setId("1");
        riskReturn.setProjectId("123");
        riskReturn.setName("Risk");
        riskReturn.setUserId("321");
        riskReturn.setDetectedDate(LocalDate.now());
        riskReturn.setEndedDate(LocalDate.of(2022, 06, 30));
        riskReturn.setLabels(List.of("Label1", "Label2"));
        riskReturn.setDescription("Descripcion1 modificada");
        riskReturn.setRiskState("Abierto");
        riskReturn.setAudience("Interna");
        riskReturn.setCategory("Costo");
        riskReturn.setRiskType("Riesgo de producto o calidad");
        riskReturn.setDetailsRiskType("Detail1");
        riskReturn.setProbability(3);
        riskReturn.setImpactValue(3);
        riskReturn.setMitigationPlan("Mitigation plan 1");
        riskReturn.setResponsibleMitigationMails(List.of("user1@gmail.com", "user2@gmail.com"));
        riskReturn.setContingencyPlan("Contingency 1");
        riskReturn.setResponsibleContingencyMails(List.of("user1@gmail.com", "user2@gmail.com"));
        riskReturn.setState(1);

        var riskDTO = riskMapper.RiskToRiskDTO().apply(risk);

        when(riskRepository.findById("1")).thenReturn(Mono.just(risk));
        when(riskRepository.save(risk)).thenReturn(Mono.just(riskReturn));

        StepVerifier.create(updateRiskUseCase.apply(riskDTO))
                .expectNext("1")
                .verifyComplete();

        verify(riskRepository).save(refEq(risk));
    }
}
