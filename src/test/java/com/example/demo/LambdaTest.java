package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class LambdaTest {
    public static void main(String[] args) {

        LT lt11 = LambdaTest :: p;

        LT lt = () -> {
            System.out.println("line 11");
            return "";
        };

        lt11.test();
        lt.test();


        LT2 lt2 = (a,b) -> a+b;
        System.out.println(lt2.testAdd(2,3));

        LT3 lt3 = System.out :: println;
        lt3.testPrint("line 24");

        List l = new ArrayList<String>();
        l.add("a");
        l.add("b");
        l.add("c");
        l.forEach(System.out::println);
    }

    private static String p() {
        System.out.println("p inner");
        return null;
    }



    interface LT {
        String test();
    }

    interface LT2{

        int testAdd(int a, int b);
    }

    interface LT3{

        void testPrint(String a);
    }
}
