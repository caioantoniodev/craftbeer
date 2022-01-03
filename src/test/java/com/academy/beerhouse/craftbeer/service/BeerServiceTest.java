package com.academy.beerhouse.craftbeer.service;

import com.academy.beerhouse.craftbeer.domain.Beer;
import com.academy.beerhouse.craftbeer.repository.BeerRepository;
import com.academy.beerhouse.craftbeer.util.BeerCreator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;
import reactor.blockhound.BlockHound;
import reactor.blockhound.BlockingOperationError;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.anyInt;
import static reactor.core.scheduler.Schedulers.parallel;

@ExtendWith(SpringExtension.class)
class BeerServiceTest {

    private final Beer beer = BeerCreator.createValidBeer();
    @InjectMocks
    private BeerService beerService;
    @Mock
    private BeerRepository beerRepository;

    @BeforeAll
    public static void blockHoundSetup() {
        BlockHound.install();
    }

    @BeforeEach
    public void setUp() {
        BDDMockito.when(beerRepository.findAll())
                .thenReturn(Flux.just(beer));

        BDDMockito.when(beerRepository.findById(anyInt()))
                .thenReturn(Mono.just(beer));
    }

    @Test
    public void blockHoundWorks() {
        try {
            var task = new FutureTask<>(() -> {
                sleep(0);
                return "";
            });
            parallel().schedule(task);

            task.get(10, TimeUnit.SECONDS);
            fail("should fail");
        } catch (Exception e) {
            assertThat(e.getCause()).isInstanceOf(BlockingOperationError.class);
        }
    }

    @Test
    @DisplayName("findAll returns a flux of beer")
    public void shouldReturnsFluxOfBeerWhenSuccessfull() {
        StepVerifier.create(beerService.findAll())
                .expectSubscription()
                .expectNext(beer)
                .verifyComplete();
    }

    @Test
    @DisplayName("findOne returns mono with beer when it exists")
    public void shouldReturnMonoOfBeerWhenSuccessfull() {
        StepVerifier.create(beerService.findOne(1))
                .expectSubscription()
                .expectNext(beer)
                .verifyComplete();
    }

    @Test
    @DisplayName("findOne returns mono error when beer when not exist")
    public void shouldReturnMonoErrorWhenEmptyMonoIsReturned() {
        BDDMockito.when(beerRepository.findById(anyInt()))
                .thenReturn(Mono.empty());

        StepVerifier.create(beerService.findOne(1))
                .expectSubscription()
                .expectError(ResponseStatusException.class)
                .verify();
    }
}