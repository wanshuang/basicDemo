package com.example.demo;

import java.sql.Time;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

/**
 * @author ws
 * @Description:
 * @date 2023/01/30
 * @ClassName SyncStaticTest
 */
public class SyncStaticTest {

    public static void main(String[] args) {
        SST s1 = new SST();
        SST s2 = new SST();
//        System.out.println("Thead A start");
//        new Thread(() -> {
//            s1.fun1();
//        }, "A").start();
//
//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println("Thead B start");
//        new Thread(() -> {
//            s1.fun2();
//        }, "B").start();
        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                s1.fun3(1);
            }).start();
        }
    }



}

 class SST {

    public synchronized static void fun1() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "-->fun1");
    }

    public synchronized  void fun2() {
        System.out.println(Thread.currentThread().getName() + "-->fun2");
    }

    public void fun3(Integer num){
        synchronized (num) {
            try {
                num++;
                System.out.println(Thread.currentThread().getName() + "-->fun3 start");
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName() + "-->fun3 end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }}
