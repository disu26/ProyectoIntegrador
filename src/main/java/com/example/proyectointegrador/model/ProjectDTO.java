package com.example.proyectointegrador.model;

import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public final class ProjectDTO {
    private String id;

    @NotBlank(message = "El nombre del proyecto es requerido")
    @Length(min = 1, max = 50, message = "El nombre del proyecto debe tener entre 3 y 50 caracteres")
    private String name;

    @NotNull(message = "La Fecha de inicio del proyecto es requerida")
    private LocalDate startDate;

    private LocalDate endingDate;

    private List<@Length(min = 1, max = 50, message = "Se requiere máximo 50 caracteres por etiqueta") String> labels;

    @NotNull(message = "El correo usuario asignado al proyecto es requerido")
    private List<
            @Email(message = "Email no valido", regexp = "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$")
                    String> emails;

    @NotBlank(message = "La descripción del proyecto es requerida")
    @Length(min = 1, max = 700, message = "La descripción del proyecto debe ser no mayor 700 caracteres")
    private String description;

    private String status = "Creado";

    private List<RiskDTO> risks;

    public ProjectDTO(String id, String name, LocalDate startDate, LocalDate endingDate, List<String> labels, List<String> emails, String description, String status) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endingDate = endingDate;
        this.labels = labels;
        this.emails = emails;
        this.description = description;
        this.status = status;
    }

    public List<RiskDTO> getRisks() {
        this.risks = Optional.ofNullable(risks).orElse(new ArrayList<>());
        return risks;
    }
}
