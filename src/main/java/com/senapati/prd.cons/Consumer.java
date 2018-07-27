package com.senapati.prd.cons;

import com.senapati.util.ApplicationConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;

public class Consumer implements Callable {

    private static final Logger logger = LoggerFactory.getLogger(Producer.class);

    BlockingQueue<String> integerBlockingQueue;

    public Consumer(BlockingQueue queue){
        this.integerBlockingQueue = queue;
    }

    public String call() {
        logger.info("Consumer thread {} started consuming at {}", Thread.currentThread().getName(), new Date());
        Random random = new Random();
        try {
            String message = integerBlockingQueue.take();
            while(!message.equals(ApplicationConstants.TERMINAL_MESSAGE)){
                logger.info("Consumer thread {} processing message {}", Thread.currentThread().getName(), message);
                Thread.sleep(random.nextInt(10) * 1000);
                message = integerBlockingQueue.take();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("Consumer thread {} finished consuming at {}", Thread.currentThread().getName(), new Date());
        return "done";
    }
}
