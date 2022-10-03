package com.example.proyectointegrador.routers;

import com.example.proyectointegrador.collections.History;
import com.example.proyectointegrador.usercase.history.GetAllHistoriesUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class HistoryRouter {

    @Bean
    public RouterFunction<ServerResponse> getAllHistories(GetAllHistoriesUseCase getAllHistoriesUseCase) {
        return route(GET("/getAllHistory"), request -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(getAllHistoriesUseCase.get(), History.class)));
    }
}
