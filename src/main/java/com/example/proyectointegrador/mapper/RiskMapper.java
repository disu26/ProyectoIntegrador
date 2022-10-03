package com.example.proyectointegrador.mapper;

import com.example.proyectointegrador.collections.Risk;
import com.example.proyectointegrador.model.RiskDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public final class RiskMapper {

    public Function<RiskDTO, Risk> RiskDTOTORisk(String id) {
        return updateRisk -> {
            var risk = new Risk();
            risk.setId(id);
            risk.setProjectId(updateRisk.getProjectId());
            risk.setName(updateRisk.getName());
            risk.setUserId(updateRisk.getUserId());
            risk.setDetectedDate(updateRisk.getDetectedDate());
            risk.setEndedDate(updateRisk.getEndedDate());
            risk.setLabels(updateRisk.getLabels());
            risk.setDescription(updateRisk.getDescription());
            risk.setRiskState(updateRisk.getRiskState());
            risk.setAudience(updateRisk.getAudience());
            risk.setCategory(updateRisk.getCategory());
            risk.setRiskType(updateRisk.getRiskType());
            risk.setDetailsRiskType(updateRisk.getDetailsRiskType());
            risk.setProbability(updateRisk.getProbability());
            risk.setImpactValue(updateRisk.getImpactValue());
            risk.setMitigationPlan(updateRisk.getMitigationPlan());
            risk.setResponsibleMitigationMails(updateRisk.getResponsibleMitigationMails());
            risk.setContingencyPlan(updateRisk.getContingencyPlan());
            risk.setResponsibleContingencyMails(updateRisk.getResponsibleContingencyMails());
            risk.setState(updateRisk.getState());
            // state = 1
            return risk;
        };
    }

    public Function<Risk, RiskDTO> RiskToRiskDTO() {
        return entity -> new RiskDTO(
                entity.getId(),
                entity.getProjectId(),
                entity.getName(),
                entity.getUserId(),
                entity.getDetectedDate(),
                entity.getEndedDate(),
                entity.getLabels(),
                entity.getDescription(),
                entity.getRiskState(),
                entity.getAudience(),
                entity.getCategory(),
                entity.getRiskType(),
                entity.getDetailsRiskType(),
                entity.getProbability(),
                entity.getImpactValue(),
                entity.getMitigationPlan(),
                entity.getResponsibleMitigationMails(),
                entity.getContingencyPlan(),
                entity.getResponsibleContingencyMails(),
                entity.getState()
        );
    }
}
