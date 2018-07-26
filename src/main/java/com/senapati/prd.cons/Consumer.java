package com.senapati.prd.cons;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.Callable;

public class Consumer implements Callable {

    private static final Logger logger = LoggerFactory.getLogger(Producer.class);

    public String call() {
        logger.info("Consumer thread {} started producing at {}", Thread.currentThread().getName(), new Date());
        Random random = new Random();
        try {
            Thread.sleep(random.nextInt(10) * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("Consumer thread {} finished producing at {}", Thread.currentThread().getName(), new Date());
        return "done";
    }
}
