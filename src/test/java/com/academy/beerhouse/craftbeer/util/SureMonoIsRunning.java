package com.academy.beerhouse.craftbeer.util;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static java.lang.Thread.sleep;
import static reactor.core.scheduler.Schedulers.parallel;

public class SureMonoIsRunning {
    public static void blockHoundWorks() throws ExecutionException, InterruptedException, TimeoutException {

        var task = new FutureTask<>(() -> {
            sleep(0);
            return "";
        });
        parallel().schedule(task);

        task.get(10, TimeUnit.SECONDS);
    }
}
