package com.thinksync.virtualthreadseries.sec01;

import com.thinksync.virtualthreadseries.VirtualThreadSeriesApplication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;

public class Task {

    private static final Logger logger = LogManager.getLogger(Task.class);
    public static void ioIntensiveTask(int i){
        try {
            logger.info("Started io task for: {} ",i);
            Thread.sleep(10_000);
            logger.info("Completed io task for: {} ",i);
        } catch (InterruptedException e) {
            logger.error("Unable to make the thread sleep!  ERR: {}",e.getMessage());
        }
    }
}
