package com.example.proyectointegrador.usercase.history;

import com.example.proyectointegrador.collections.History;
import com.example.proyectointegrador.repositories.HistoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;

@Service
@Validated
public class GetAllHistoriesUseCase implements Supplier<Flux<History>> {

    private final HistoryRepository historyRepository;

    public GetAllHistoriesUseCase(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    @Override
    public Flux<History> get() {
        return historyRepository.findAll();
    }
}
