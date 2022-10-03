package com.example.proyectointegrador.collections;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "risks")
public final class Risk {
    @Id
    private String id;

    private String projectId;

    private String name;

    private String userId;

    private LocalDate detectedDate = LocalDate.now();

    private LocalDate endedDate;

    private List<String> labels;

    private String description;

    // (Abierto; mitigado; Cerrado; Problema)
    private String riskState;

    // (Interna; Externa)
    private String audience;

    // (Costo; Tiempo; Calidad)
    private String category;

    // (Riesgo de producto o calidad; Riesgo de proyecto)
    private String riskType;

    private String detailsRiskType;

    private Integer probability;

    private Integer impactValue;

    private String mitigationPlan;

    private List<String> responsibleMitigationMails;

    private String contingencyPlan;

    private List<String> responsibleContingencyMails;

    private Integer state = 1;
}
