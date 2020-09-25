package com.example.demo;

import org.junit.jupiter.api.Test;

/**
 * @author ws
 * @date 2020/9/18
 */
public class IntegerDemo {

    @Test
    public void testmain() {
        test(10,10);
    }

    public void test(Integer a , Integer b){
        System.out.println(a == b);
    }


}
