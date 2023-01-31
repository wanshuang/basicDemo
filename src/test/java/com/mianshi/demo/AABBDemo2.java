package com.mianshi.demo;

import java.util.ArrayList;
import java.util.List;

public class AABBDemo2 {
    public static void main(String[] args) {
        List<String> str = new ArrayList<String>();
        str.add("aabbbbcc");
        str.stream().forEach(s-> {
            System.out.println(s.replaceAll("(.)\\1+", "$1$1").replaceAll("(.)\\1(.)\\2", "$1$1$2"));
        });
    }
}
