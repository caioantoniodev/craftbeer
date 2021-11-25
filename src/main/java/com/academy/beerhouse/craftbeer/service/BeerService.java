package com.academy.beerhouse.craftbeer.service;

import com.academy.beerhouse.craftbeer.domain.Beer;
import com.academy.beerhouse.craftbeer.repository.BeerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class BeerService {
    private final BeerRepository beerRepository;

    public Flux<Beer> findAll() {
        return beerRepository.findAll();
    }
}
