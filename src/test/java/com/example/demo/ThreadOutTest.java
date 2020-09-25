package com.example.demo;

import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ws
 * @date 2020/8/17
 */
public class ThreadOutTest {

    final String[] a = {"1", "2", "3", "4", "5", "6", "7"};
    final String[] b = {"a", "b", "c", "d", "e", "f", "g"};

    volatile int flag = 0;

    Thread t1, t2 = null;


    /**
     * LockSupport
     */
    @Test
    public void test1() {
        t1 = new Thread(() -> {
            LockSupport.park();
            for (int foot = 0; foot < a.length; foot++) {
                System.out.println(a[foot]);
                LockSupport.unpark(t2);
                LockSupport.park();
            }
        });

        t2 = new Thread(() -> {
            for (int foot = 0; foot < b.length; foot++) {
                System.out.println(b[foot]);
                LockSupport.unpark(t1);
                LockSupport.park();
            }
        });

        t1.start();
        t2.start();

    }


    /**
     * Spinlock
     */
    @Test
    public void test2() {

        new Thread(() -> {
            int foot = 0;
            while (true) {
                if (flag == 1 && foot < a.length) {
                    System.out.println(a[foot]);
                    foot++;
                    flag = 0;
                }
            }
        }).start();

        new Thread(() -> {
            int foot = 0;
            while (true) {
                if (flag == 0 && foot < b.length) {
                    System.out.println(b[foot]);
                    foot++;
                    flag = 1;
                }
            }
        }).start();
    }

    /**
     * wait notify
     */
    @Test
    public void test3() {
        Object o = new Object();
        new Thread(() -> {
            synchronized (o) {
                try {
                    wait();
                    for (int foot = 0; foot < a.length; foot++) {
                        System.out.println(a[foot]);
                        notifyAll();
                        wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (o) {
                try {
                    for (int foot = 0; foot < b.length; foot++) {
                        System.out.println(b[foot]);
                        notifyAll();
                        wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    BlockingQueue<String> b1 = new ArrayBlockingQueue(1);

    BlockingQueue<String> b2 = new ArrayBlockingQueue(1);

    /**
     * BlockingQueue
     */
    @Test
    public void test4() {

        new Thread(() -> {
            try {
                b2.put("ok");
                for (int index = 0; index < a.length; index++) {
                    b1.take();
                    System.out.println(a[index]);
                    b2.put("ok");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                for (int index = 0; index < b.length; index++) {
                    b2.take();
                    System.out.println(b[index]);
                    b1.put("ok");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }

    Lock lock = new ReentrantLock();
    Condition c1 = lock.newCondition();
    Condition c2 = lock.newCondition();

    @Test
    public void test5(){

        new Thread(()->{
            try{
                lock.lock();
                c1.await();
                for (int index = 0; index < a.length; index++) {
                    System.out.println(a[index]);
                    c2.signal();
                    c1.await();
                }
                c2.signal();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }).start();

        new Thread(()->{
            try{
                lock.lock();
                for (int index = 0; index < a.length; index++) {
                    System.out.println(b[index]);
                    c1.signal();
                    c2.await();
                }
                c1.signal();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }).start();

    }

}
