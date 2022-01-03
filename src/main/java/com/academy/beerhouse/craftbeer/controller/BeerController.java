package com.academy.beerhouse.craftbeer.controller;

import com.academy.beerhouse.craftbeer.domain.Beer;
import com.academy.beerhouse.craftbeer.service.BeerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

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
    public Mono<Beer> findOne(@PathVariable Integer id) {
        return beerService.findOne(id);
    }

    @PutMapping
    @ResponseStatus(NO_CONTENT)
    public Mono<Void> updateOne(@Valid @RequestBody Beer beer) {
        return beerService.updateOne(beer);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Mono<Beer> createOne(@Valid @RequestBody Beer beer) {
        return beerService.createOne(beer);
    }
}
