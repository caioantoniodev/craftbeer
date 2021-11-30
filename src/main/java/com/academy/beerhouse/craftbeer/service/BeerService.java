package com.academy.beerhouse.craftbeer.service;

import com.academy.beerhouse.craftbeer.domain.Beer;
import com.academy.beerhouse.craftbeer.repository.BeerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class BeerService {
    private final BeerRepository beerRepository;

    public Flux<Beer> findAll() {
        return beerRepository.findAll();
    }

    public Mono<Beer> findOne(UUID id) {
        return beerRepository.findById(id)
                .switchIfEmpty(monoResponseStatusNotFoundException())
                .log();
    }

    private <T> Mono<T> monoResponseStatusNotFoundException() {
        return Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Beer not found"));
    }
}
