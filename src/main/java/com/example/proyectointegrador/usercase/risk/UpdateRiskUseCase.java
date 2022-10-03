package com.example.proyectointegrador.usercase.risk;

import com.example.proyectointegrador.collections.History;
import com.example.proyectointegrador.mapper.RiskMapper;
import com.example.proyectointegrador.model.RiskDTO;
import com.example.proyectointegrador.repositories.HistoryRepository;
import com.example.proyectointegrador.repositories.RiskRepository;
import com.example.proyectointegrador.usercase.project.GetProjectUseCase;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Service
@Validated
public class UpdateRiskUseCase implements SaveRisk{

    private final RiskRepository riskRepo;
    private final RiskMapper riskMapper;
    private final HistoryRepository historyRepository;
    private final GetProjectUseCase getProjectUseCase;

    public UpdateRiskUseCase(RiskMapper riskMapper, RiskRepository riskRepo,
                             HistoryRepository historyRepository, GetProjectUseCase getProjectUseCase) {
        this.riskRepo = riskRepo;
        this.riskMapper = riskMapper;
        this.historyRepository = historyRepository;
        this.getProjectUseCase = getProjectUseCase;
    }

    public Mono<String> apply(RiskDTO riskDTO){
        Objects.requireNonNull(riskDTO.getId(), "Id of the risk is required");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        return riskRepo
                .save(riskMapper.RiskDTOTORisk(riskDTO.getId()).apply(riskDTO))
                .flatMap(risk -> getProjectUseCase.apply(risk.getProjectId()))
                .flatMap(projectDTO -> {
                    History history = new History(null, LocalDate.now(), LocalTime.now().format(dtf), projectDTO);
                    return historyRepository.save(history);
                })
                .map(History::getId);
    }
}
