package com.example.proyectointegrador.collections;

import com.example.proyectointegrador.model.ProjectDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "history")
public final class History {
    @Id
    private String id;
    //private String email;
    private LocalDate date;
    private String time;
    private ProjectDTO project;
}
