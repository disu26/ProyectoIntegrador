package com.example.proyectointegrador.collections;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "projects")
public final class Project {

    @Id
    private String id;
    private String name;
    private LocalDate startDate;
    private LocalDate endingDate;
    private List<String> labels;
    private List<String> emails;
    private String description;
    private String status;
}
