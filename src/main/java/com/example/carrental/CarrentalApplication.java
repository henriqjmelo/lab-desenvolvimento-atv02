package com.example.carrental;

import io.micronaut.runtime.Micronaut;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CarrentalApplication {

    private static final CountDownLatch KEEP_ALIVE = new CountDownLatch(1);

    public static void main(String[] args) {
        Micronaut.run(CarrentalApplication.class, args);
        try {
            while (true) {
                KEEP_ALIVE.await(1, TimeUnit.DAYS);
            }
        } catch (InterruptedException ignored) {
            Thread.currentThread().interrupt();
        }
    }

}

