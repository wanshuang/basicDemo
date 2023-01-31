package com.example.demo;

import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @author ws
 * @date 2020/9/1
 */
public class ThreadCountTest {

    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(5);

    public static class Surmount implements Runnable{
        @Override
        public void run() {

            try {
                for(int i = 1; i < 4; i++){
                    Random rand = new Random();
                    int randomNum = rand.nextInt(5);//产生1000到3000之间的随机整数
                    TimeUnit.SECONDS.sleep(randomNum);
                    String name = Thread.currentThread().getName();
                    System.out.println(name+"翻过了第" + i +"个障碍");
                    cyclicBarrier.await();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test() {
        for (int i = 1; i < 6; i++){
            Thread thread = new Thread(new Surmount(),"选手"+ i );
            thread.start();
        }
        System.out.println("test is end");
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

//        Semaphore semaphore = new Semaphore(2);
//        for (int i = 0 ; i < 6 ; i ++){
//            new Thread(()->{
//                try {
//                    semaphore.acquire();
//                    System.out.println(Thread.currentThread().getName()+"获取许可");
//                    TimeUnit.SECONDS.sleep(2);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }finally {
//                    System.out.println(Thread.currentThread().getName()+"释放许可");
//                    semaphore.release();
//                }
//            },i+"").start();
//        }

        for (int i = 1; i < 6; i++){
            Thread thread = new Thread(new Surmount(),"选手"+ i );
            thread.start();
        }
        System.out.println("main is end");
    }

}
