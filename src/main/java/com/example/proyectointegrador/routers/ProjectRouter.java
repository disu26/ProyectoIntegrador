package com.example.proyectointegrador.routers;

import com.example.proyectointegrador.model.ProjectDTO;
import com.example.proyectointegrador.usercase.project.*;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.Function;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ProjectRouter {

    //Crear un proyecto
    @Bean
    @RouterOperation(beanClass = CreateProjectUseCase.class, beanMethod = "apply",
            operation = @Operation(operationId = "ProjectDTO", summary = "Crear proyecto", tags = {"proyecto"},
                    responses = {@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = ProjectDTO.class))),
                            @ApiResponse(responseCode = "400", description = "Invalid Project supplied"),
                            @ApiResponse(responseCode = "404", description = "Project not found")}))
    public RouterFunction<ServerResponse> createProject(CreateProjectUseCase createProjectUseCase) {
        Function<ProjectDTO, Mono<ServerResponse>> executor = projectDTO -> createProjectUseCase.apply(projectDTO)
                .flatMap(result -> ServerResponse.ok()
                        .contentType(MediaType.TEXT_PLAIN)
                        .bodyValue(result));
        return route(
                POST("/createProject").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(ProjectDTO.class).flatMap(executor)
        );
    }

    //Consultar Todos los projectos
    @Bean
    @RouterOperation(beanClass = GetAllProjectsUseCase.class, beanMethod = "get",
            operation = @Operation(operationId = "Consultar", summary = "Consultar todos los proyectos", tags = {"Proyecto"}))
    public RouterFunction<ServerResponse> getAllProjects(GetAllProjectsUseCase getAllProjectsUseCase) {
        return route(GET("/getAllProjects"), request -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(getAllProjectsUseCase.get(), ProjectDTO.class)));
    }

    //Consultar un proyecto por su id
    @Bean
    @RouterOperation(beanClass = GetProjectUseCase.class, beanMethod = "apply",
            operation = @Operation(operationId = "ConsultarById", summary = "Consultar proyecto por ID", tags = {"Proyecto"},
                    parameters = {@Parameter(in = ParameterIn.PATH, name = "id", description = "String")},
                    responses = {@ApiResponse(responseCode = "200", description = "successful operation",
                            content = @Content(schema = @Schema(implementation = ProjectDTO.class))),
                            @ApiResponse(responseCode = "400", description = "Invalid project supplied"),
                            @ApiResponse(responseCode = "404", description = "Project not found")}))
    public RouterFunction<ServerResponse> getProjectById(GetProjectUseCase getProjectUseCase) {
        return route(
                GET("/getProjectById/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getProjectUseCase.apply(
                                        request.pathVariable("id")),
                                ProjectDTO.class
                        ))
        );
    }

    //    Modificar un proyecto
    @Bean
    @RouterOperation(beanClass = UpdateProjectUseCase.class, beanMethod = "apply",
            operation = @Operation(operationId = "Modificar", summary = "Modificar Proyecto", tags = {"Proyecto"},
                    parameters = {@Parameter(in = ParameterIn.PATH, name = "id", description = "String"),
                            @Parameter(in = ParameterIn.PATH, name = "name", description = "String"),
                            @Parameter(in = ParameterIn.PATH, name = "startDate", description = "String"),@Parameter(in = ParameterIn.PATH, name = "endiDate", description = "String"),
                            @Parameter(in = ParameterIn.PATH, name = "labels", description = "List<String>"),
                            @Parameter(in = ParameterIn.PATH, name = "emails", description = "List<String>"),@Parameter(in = ParameterIn.PATH, name = "description", description = "String"),@Parameter(in = ParameterIn.PATH, name = "status", description = "String"),},
                    responses = {@ApiResponse(responseCode = "200", description = "successful operation"),
                            @ApiResponse(responseCode = "400", description = "Invalid project supplied"),
                            @ApiResponse(responseCode = "404", description = "Project not found")}))
    public RouterFunction<ServerResponse> updateProject(UpdateProjectUseCase updateProjectUseCase) {
        Function<ProjectDTO, Mono<ServerResponse>> executor = projectDTO -> updateProjectUseCase.apply(projectDTO)
                .flatMap(result -> ServerResponse.ok()
                        .contentType(MediaType.TEXT_PLAIN)
                        .bodyValue(result));
        return route(
                PUT("/updateProject").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(ProjectDTO.class).flatMap(executor)
        );
    }

    //    Eliminar un proyecto
    @Bean
    @RouterOperation(beanClass = DeleteProjectUseCase.class, beanMethod = "apply",
            operation = @Operation(operationId = "deleteProject", summary = "Eliminar proyecto", tags = {"proyecto"},
                    parameters = {@Parameter(in = ParameterIn.PATH, name = "id", description = "String")},
                    responses = {@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = ProjectDTO.class))),
                            @ApiResponse(responseCode = "400", description = "Invalid project supplied"),
                            @ApiResponse(responseCode = "404", description = "Project not found")}))
    public RouterFunction<ServerResponse> deleteProject(DeleteProjectUseCase deleteProjectUseCase) {
        return route(
                DELETE("/deleteProject/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.accepted()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(deleteProjectUseCase.apply(request.pathVariable("id")), Void.class))
        );
    }
}
