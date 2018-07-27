package com.senapati.prd.cons;

import com.senapati.util.ApplicationConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

public class Producer implements Callable {

    private static final Logger logger = LoggerFactory.getLogger(Producer.class);

    private BlockingQueue<String> blockingQueue;
    private AtomicInteger messageCount;

    Producer(BlockingQueue blockingQueue, Integer messageCount){
        this.blockingQueue = blockingQueue;
        this.messageCount = new AtomicInteger(messageCount);
    }

    public Object call()  {
        logger.info("producer thread {} started producing at {}", Thread.currentThread().getName(), new Date());
        Random random = new Random();
        try {
            while(messageCount.get() > 0){
                String message = new StringBuilder(Thread.currentThread().getName())
                        .append("-----")
                        .append(messageCount)
                        .toString();
                blockingQueue.put(message);
                Thread.sleep(random.nextInt(10) * 1000);
                messageCount.decrementAndGet();
            }
            blockingQueue.put(ApplicationConstants.TERMINAL_MESSAGE);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("producer thread {} finished producing at {}", Thread.currentThread().getName(), new Date());
        return "DONE";
    }
}
