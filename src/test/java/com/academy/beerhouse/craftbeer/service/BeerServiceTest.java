package com.academy.beerhouse.craftbeer.service;

import com.academy.beerhouse.craftbeer.domain.Beer;
import com.academy.beerhouse.craftbeer.repository.BeerRepository;
import com.academy.beerhouse.craftbeer.util.BeerCreator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.blockhound.BlockHound;
import reactor.blockhound.BlockingOperationError;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static reactor.core.scheduler.Schedulers.*;

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
}