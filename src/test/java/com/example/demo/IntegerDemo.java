package com.example.demo;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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


    @Test
    public void list(){
        List<String> arraysList = Arrays.asList("1", "2", "3", "4");

        List<String> listStr = new ArrayList<>();
        listStr.add("6");
        listStr.addAll(arraysList);
        listStr.forEach(x->System.out.println(x));
    }
}
