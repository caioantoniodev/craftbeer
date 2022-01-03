package com.academy.beerhouse.craftbeer.util;

import com.academy.beerhouse.craftbeer.domain.Beer;

import java.math.BigDecimal;

public class BeerCreator {
    public static Beer createBeerToBeSaved() {
        return Beer.builder()
                .name("Beer")
                .description("Beer Description")
                .price(new BigDecimal(12.8))
                .alcohol_by_volume(6.7)
                .type("Type")
                .build();
    }

    public static Beer createValidBeer() {
        return Beer.builder()
                .id(1)
                .name("Beer")
                .description("Beer Description")
                .price(new BigDecimal(12.8))
                .alcohol_by_volume(6.7)
                .type("Type")
                .build();
    }

    public static Beer createValidUpdatedBeer() {
        return Beer.builder()
                .id(1)
                .name("Beer Updated")
                .description("Beer Description Updated")
                .price(new BigDecimal(12.9))
                .alcohol_by_volume(6.9)
                .type("Type Updated")
                .build();
    }
}
