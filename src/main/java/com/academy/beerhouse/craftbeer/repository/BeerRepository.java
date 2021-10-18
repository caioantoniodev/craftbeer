package com.academy.beerhouse.craftbeer.repository;

import com.academy.beerhouse.craftbeer.domain.Beer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;

public interface BeerRepository extends ReactiveCrudRepository<Beer, UUID> {

}
