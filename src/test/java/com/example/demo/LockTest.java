package com.example.demo;

import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * @author ws
 * @date 2020/9/3
 */
public class LockTest {

    Lock lock = new ReentrantLock();

    @Test
    public void test(){
        Thread t1 = new Thread(()->{
            System.out.println(Thread.currentThread().getName()+ "startlock");
            lock.lock();
            System.out.println(Thread.currentThread().getName()+ "lock");
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
                System.out.println(Thread.currentThread().getName()+ "unlock");
            }
        },"t1");

        Thread t2 = new Thread(()->{
            System.out.println(Thread.currentThread().getName()+ "startlock");
            try {
                lock.tryLock(10,TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+ "lock");
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
                System.out.println(Thread.currentThread().getName()+ "unlock");
            }
        },"t2");

        Thread t3 = new Thread(()->{
            System.out.println(Thread.currentThread().getName()+ "startlock");
            lock.lock();
            System.out.println(Thread.currentThread().getName()+ "lock");
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
                System.out.println(Thread.currentThread().getName()+ "unlock");
            }
        },"t3");


        t1.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        t2.interrupt();
//        System.out.println("t2 interrupt");

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
