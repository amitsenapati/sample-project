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

    public String call()  {
        logger.info("started producing at {}", new Date());
        Random random = new Random();
        try {
            while(messageCount.decrementAndGet() > 0){
                String message = new StringBuilder(Thread.currentThread().getName())
                        .append("-----")
                        .append(messageCount)
                        .toString();
                logger.info("putting message {} to queue", message);
                blockingQueue.put(message);
                Thread.sleep(random.nextInt(3) * 1000);
            }
            blockingQueue.put(ApplicationConstants.TERMINAL_MESSAGE);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("finished producing at {}", new Date());
        return "DONE";
    }
}
