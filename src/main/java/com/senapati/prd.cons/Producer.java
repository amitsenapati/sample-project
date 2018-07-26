package com.senapati.prd.cons;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;

public class Producer implements Callable {

    private static final Logger logger = LoggerFactory.getLogger(Producer.class);

    private BlockingQueue<Integer> blockingQueue;

    Producer(BlockingQueue blockingQueue){
        this.blockingQueue = blockingQueue;
    }

    public Object call()  {
        logger.info("producer thread {} started producing at {}", Thread.currentThread().getName(), new Date());
        Random random = new Random();
        try {
//            while()
            Thread.sleep(random.nextInt(10) * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("producer thread {} finished producing at {}", Thread.currentThread().getName(), new Date());
        return "DONE";
    }
}
