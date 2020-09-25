package com.example.demo;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

/**
 * @author ws
 * @date 2020/9/1
 */
public class ThreadCountTest {

    @Test
    public void test() {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for(int i = 0 ; i < 6 ; i++){
            new Thread(()->{
               System.out.println("Theard " + Thread.currentThread().getName() + " go");
               countDownLatch.countDown();
            },i+"").start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("done");
    }


    @Test
    public void test2(){
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,()->{
            System.out.println("召唤神龙!");
        });

        for(int i = 0 ; i < 7 ; i ++){

            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"龙珠获取!");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"仪式结束!");
            },i+"").start();
        }
    }


    public static void main(String[] args) {

        Semaphore semaphore = new Semaphore(2);
        for (int i = 0 ; i < 6 ; i ++){
            new Thread(()->{
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"获取许可");
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    System.out.println(Thread.currentThread().getName()+"释放许可");
                    semaphore.release();
                }
            },i+"").start();
        }


    }

}
