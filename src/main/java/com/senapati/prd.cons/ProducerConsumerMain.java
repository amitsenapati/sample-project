package com.senapati.prd.cons;

import com.senapati.util.ApplicationConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ProducerConsumerMain {

    private static final int MAX_THREADS = 4;

    private static final Logger logger = LoggerFactory.getLogger(ProducerConsumerMain.class);

    public static void main(String args[]){


        BlockingQueue<String> integerBlockingQueue = new ArrayBlockingQueue<String>(ApplicationConstants.QUEUE_SIZE);

        Producer producer = new Producer(integerBlockingQueue, ApplicationConstants.TOTAL_MESSAGES_TO_PRODUCE);
        Consumer consumer = new Consumer(integerBlockingQueue);

        List<Callable<String>> callableTasks = new ArrayList<Callable<String>>();
        callableTasks.add(producer);
        callableTasks.add(producer);
        callableTasks.add(producer);
        callableTasks.add(producer);

        callableTasks.add(consumer);
        callableTasks.add(consumer);
        callableTasks.add(consumer);
        callableTasks.add(consumer);

        ExecutorService executorService = Executors.newFixedThreadPool(MAX_THREADS);
        try {
            List<Future<String>> futures = executorService.invokeAll(callableTasks);
        } catch (InterruptedException e) {
            logger.error("Error in thread execution");
        }
        executorService.shutdown();
    }
}
