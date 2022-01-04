package com.academy.beerhouse.craftbeer.service;

import com.academy.beerhouse.craftbeer.domain.Beer;
import com.academy.beerhouse.craftbeer.mock.BeerMock;
import com.academy.beerhouse.craftbeer.repository.BeerRepository;
import org.assertj.core.api.Fail;
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

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import static com.academy.beerhouse.craftbeer.util.SureBlockHoundIsRunning.blockHoundWorks;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

@ExtendWith(SpringExtension.class)
class BeerServiceTest {

    private final Beer beer = BeerMock.createValidBeer();

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

        BDDMockito.when(beerRepository.save(BeerMock.createBeerToBeSaved()))
                .thenReturn(Mono.just(beer));

        BDDMockito.when(beerRepository.delete(any(Beer.class)))
                .thenReturn(Mono.empty());
    }

    @Test
    @DisplayName("verify if the BlockHound is working ")
    void shouldBlockHoundWorks() {
        try {
            blockHoundWorks();

            Fail.fail("should fail");
        } catch (ExecutionException | InterruptedException | TimeoutException e) {
            assertThat(e.getCause()).isInstanceOf(BlockingOperationError.class);
        }
    }

    @Test
    @DisplayName("findAll returns a flux of beer")
    void shouldReturnsFluxOfBeerWhenSuccessful() {
        StepVerifier.create(beerService.findAll())
                .expectSubscription()
                .expectNext(beer)
                .verifyComplete();
    }

    @Test
    @DisplayName("findOne returns mono with beer when it exists")
    void shouldReturnMonoOfBeerWhenSuccessful() {
        StepVerifier.create(beerService.findOne(1))
                .expectSubscription()
                .expectNext(beer)
                .verifyComplete();
    }

    @Test
    @DisplayName("findOne returns mono error when beer when not exist")
    void shouldReturnMonoErrorWhenEmptyMonoIsReturned() {
        BDDMockito.when(beerRepository.findById(anyInt()))
                .thenReturn(Mono.empty());

        StepVerifier.create(beerService.findOne(1))
                .expectSubscription()
                .expectError(ResponseStatusException.class)
                .verify();
    }

    @Test
    @DisplayName("createOne creates an beer when successful")
    void shouldCreateOneBeerWhenSuccessful() {
        var beerToBeSaved = BeerMock.createBeerToBeSaved();

        StepVerifier.create(beerService.createOne(beerToBeSaved))
                .expectSubscription()
                .expectNext(beer)
                .verifyComplete();
    }

    @Test
    @DisplayName("deleteOne removes the beer when it exists")
    void shouldDeleteOneBeerWhenSuccessful() {
        StepVerifier.create(beerService.deleteOne(1))
                .expectSubscription()
                .verifyComplete();
    }

    @Test
    @DisplayName("deleteOne returns mono error when beer when not exist")
    void shouldNotDeleteOneBeerWhenReturnMonoError() {
        BDDMockito.when(beerRepository.findById(anyInt()))
                .thenReturn(Mono.empty());

        StepVerifier.create(beerService.deleteOne(1))
                .expectSubscription()
                .expectError(ResponseStatusException.class)
                .verify();
    }
}
