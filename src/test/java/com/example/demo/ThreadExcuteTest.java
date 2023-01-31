package com.example.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadExcuteTest {

    public static void main(String[] args) {
        ExecutorService  executor = Executors.newCachedThreadPool();
        executor.submit(()->{

        });
        executor.execute(()->{

        });
    }
}
