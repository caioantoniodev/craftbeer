package com.academy.beerhouse.craftbeer.controller;

import com.academy.beerhouse.craftbeer.domain.Beer;
import com.academy.beerhouse.craftbeer.service.BeerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("beerhouse/v1/beers")
@Slf4j
public class BeerController {
    private final BeerService beerService;

    @GetMapping
    public Flux<Beer> findAll() {
        return beerService.findAll();
    }

    @GetMapping(path = "{id}")
    public Mono<Beer> findOne(@PathVariable UUID id) {
        return beerService.findOne(id);
    }
}
