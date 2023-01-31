package com.example.demo;

import org.junit.jupiter.api.Test;

/**
 * @author ws
 * @date 2020/8/14
 */
public class ThreadLocalTest {

    ThreadLocal<String> threadLocal = new ThreadLocal();

    @Test
    public void test(){

        new Thread(()->{
            threadLocal.set("a");
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"_"+threadLocal.get());
            },"s3").start();
            System.out.println(Thread.currentThread().getName()+"_"+threadLocal.get());
        },"s1").start();

//        new Thread(()->{
//            System.out.println(threadLocal.get());
//        },"s2").start();

    }


    private ThreadLocal<Integer> TL_INT = ThreadLocal.withInitial(() -> 6);
    private ThreadLocal<String> TL_STRING = ThreadLocal.withInitial(() -> "Hello, world");

    @Test
    public void test2() {
        // 6
        System.out.println(TL_INT.get());
        TL_INT.set(TL_INT.get() + 1);
        // 7
        System.out.println(TL_INT.get());
        TL_INT.remove();
        // 会重新初始化该value，6
        System.out.println(TL_INT.get());
    }

}
