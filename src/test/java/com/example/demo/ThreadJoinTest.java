package com.example.demo;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

/**
 * @author ws
 * @date 2020/8/17
 */
public class ThreadJoinTest {

    int i = 0;

    @Test
    public void test() {
        Thread t1 = new Thread(() -> {
            for (int x = 0; x < 100000; x++) {
                i++;
            }
            System.out.println(i);
            System.out.println("test Thread end");
        });

        t1.start();
//        try {
//            t1.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println(i);
        System.out.println("main Thread end");
    }

    @Test
    public void test2() {
        FutureTask ft = new FutureTask<Integer>(() -> {
            for (int x = 0; x < 100000; x++) {
                i++;
            }
            System.out.println(i);
            System.out.println("test Thread end");
            return i;
        });
        System.out.println(i);
//        try {
//            System.out.println(ft.get());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
        System.out.println("main Thread end");
    }

    @Test
    public void test3(){
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future f = executorService.submit(()->{
            for (int x = 0; x < 100000; x++) {
                i++;
            }
            System.out.println(i);
            System.out.println("test Thread end");
            return i;
        });
        System.out.println(i);
        try {
            System.out.println(f.isDone());
            System.out.println(f.get());
            System.out.println(f.isDone());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("main Thread end");

    }


}
