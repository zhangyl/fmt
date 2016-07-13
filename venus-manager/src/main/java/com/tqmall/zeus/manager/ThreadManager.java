package com.tqmall.zeus.manager;

import org.springframework.stereotype.Component;

import java.util.concurrent.*;

/**
 * Created by zyl on 3/25/16.
 */
@Component
public class ThreadManager {

    private static final ExecutorService executor = Executors.newFixedThreadPool(3, new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        }
    });

    public void execute(Runnable task) {
        executor.execute(task);
    }

}
