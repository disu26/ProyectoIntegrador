package com.example.proyectointegrador.usercase.risk;

import com.example.proyectointegrador.collections.Project;
import com.example.proyectointegrador.mapper.RiskMapper;
import com.example.proyectointegrador.model.RiskDTO;
import com.example.proyectointegrador.repositories.ProjectRepository;
import com.example.proyectointegrador.repositories.RiskRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class CreateRiskUseCase implements SaveRisk {

    private final RiskRepository riskRepo;
    private final RiskMapper riskMapper;
    private final ProjectRepository projectRepository;

    public CreateRiskUseCase(RiskMapper riskMapper, RiskRepository riskRepo, ProjectRepository projectRepository) {
        this.riskRepo = riskRepo;
        this.riskMapper = riskMapper;
        this.projectRepository = projectRepository;
    }

    public Mono<String> apply(RiskDTO riskDTO) {
        if (riskDTO.getEndedDate() == null) return guarda(riskDTO);
        if(riskDTO.getDetectedDate().isBefore(riskDTO.getEndedDate()) ||
                riskDTO.getDetectedDate().isEqual(riskDTO.getEndedDate())) return guarda(riskDTO);
        return Mono.error(new IllegalArgumentException("La fecha de inicio debe ser igual o anterior a la fecha de finalización"));
    }

    public Mono<String> guarda(RiskDTO riskDTO){
        return riskRepo
                .save(riskMapper.RiskDTOTORisk(null).apply(riskDTO))
                .flatMap(risk -> projectRepository.findById(risk.getProjectId()))
                .flatMap(project -> {
                    project.setStatus("Cerrado");
                    return projectRepository.save(project);
                })
                .map(Project::getId)
                .switchIfEmpty(Mono.defer(() -> Mono.just("Empty")));
    }
}
