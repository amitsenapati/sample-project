package com.senapati.prd.cons;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ProducerConsumerMain {

    private static final int MAX_THREADS = 4;

    private static final Logger logger = LoggerFactory.getLogger(ProducerConsumerMain.class);

    public static void main(String args[]){

        Producer producer = new Producer();
        Consumer consumer = new Consumer();

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
