package com.example.proyectointegrador.mapper;

import com.example.proyectointegrador.collections.Project;
import com.example.proyectointegrador.model.ProjectDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public final class ProjectMapper {

    public Function<ProjectDTO, Project> mapperToProject(String id){
        return updateProject -> {
            var project = new Project();
            project.setId(id);
            project.setName(updateProject.getName());
            project.setStartDate(updateProject.getStartDate());
            project.setEndingDate(updateProject.getEndingDate());
            project.setLabels(updateProject.getLabels());
            project.setEmails(updateProject.getEmails());
            project.setDescription(updateProject.getDescription());
            project.setStatus(updateProject.getStatus());
            return project;
        };
    }

    public Function<Project, ProjectDTO> entityToProjectDTO(){
        return entity -> new ProjectDTO(
                entity.getId(),
                entity.getName(),
                entity.getStartDate(),
                entity.getEndingDate(),
                entity.getLabels(),
                entity.getEmails(),
                entity.getDescription(),
                entity.getStatus()
        );
    }
}
