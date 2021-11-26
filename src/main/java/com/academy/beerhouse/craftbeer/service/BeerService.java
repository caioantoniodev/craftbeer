package com.academy.beerhouse.craftbeer.service;

import com.academy.beerhouse.craftbeer.domain.Beer;
import com.academy.beerhouse.craftbeer.repository.BeerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BeerService {
    private final BeerRepository beerRepository;

    public Flux<Beer> findAll() {
        return beerRepository.findAll();
    }

    public Mono<Beer> findOne(UUID id) {
        return beerRepository.findById(id);
    }
}
