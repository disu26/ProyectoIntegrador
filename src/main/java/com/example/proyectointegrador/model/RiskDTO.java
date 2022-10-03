package com.example.proyectointegrador.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public final class RiskDTO {

    private String id;

    @NotBlank
    private String projectId;

    @NotBlank(message = "El nombre del riesgo es requerido")
    @Length(min = 1, max = 50, message = "La longitud del nombre debe ser menor a 50 caracteres")
    private String name;

    @NotBlank
    private String userId;

    @NotNull
    private LocalDate detectedDate;

    //@NotBlank
    private LocalDate endedDate;

    private List<
                @Length(min = 1, max = 50, message = "La longitud de una etiqueta debe ser menor a 50 caracteres y no puede ser vacia")
                        String> labels;

    @NotBlank
    @Length(min = 1, max = 700, message = "La longitud de la descripcion debe ser menor a 700 caracteres")
    private String description;

    // (Abierto; mitigado; Cerrado; Problema)
    @NotBlank
    @Pattern(regexp="^(Abierto|Mitigado|Cerrado|Problema)$",message="El estado debe ser: Abierto o Mitigado o Cerrado o Problema")
    private String riskState;

    // (Interna; Externa)
    @NotBlank
    @Pattern(regexp="^(Interna|Externa)$",message="La audiencia debe ser: Interna o Externa")
    private String audience;

    // (Costo; Tiempo; Calidad)
    @NotBlank
    @Pattern(regexp="^(Costo|Tiempo|Calidad)$",message="La categoria debe ser: Costo o Tiempo o Calidad")
    private String category;

    // (Riesgo de producto o calidad; Riesgo de proyecto)
    @NotBlank
    @Pattern(regexp="^(Riesgo de producto o calidad|Riesgo de proyecto)$",message="El tipo de riesgo debe ser: (Riesgo de producto o calidad) o (Riesgo de proyecto)")
    private String riskType;

    @NotBlank
    private String detailsRiskType;

    @NotNull
    @Min(value = 1, message = "El valor de probabilidad mínimo es 1")
    @Max(value = 5, message = "El valor de probabilidad maximo es 5")
    private Integer probability;

    @NotNull
    @Min(value = 1, message = "El valor de impacto mínimo es 1")
    @Max(value = 5, message = "El valor de impacto maximo es 5")
    private Integer impactValue;

    @NotBlank
    private String mitigationPlan;

    private List<
            @Email(message = "Email is not valid", regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
                    String> responsibleMitigationMails;

    @NotBlank
    private String contingencyPlan;

    private List<
            @Email(message = "Email is not valid", regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
                    String> responsibleContingencyMails;

    private Integer state;

}
