package com.example.proyectointegrador.usercase.risk;

import com.example.proyectointegrador.model.RiskDTO;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@FunctionalInterface
public interface SaveRisk {
    Mono<String> apply(@Valid RiskDTO riskDTO);
}
