package com.mianshi.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AABBDemo {

    public static void main(String[] args) {
        String s = "aabbbcc";
        char[] chars = s.toCharArray();
        List<String> sList = new ArrayList<>();
        for (int index = 0; index < chars.length; index++) {
            sList.add(String.valueOf(chars[index]));
        }

        checkList(sList, 2);
        StringBuilder sb = new StringBuilder();
        sList.stream().forEach(string -> sb.append(string));
        System.out.println(sb);
    }

    public static void checkList(List str, int index) {
        if (index < str.size()) {
            //判断AAA
            if (str.get(index - 1).equals(str.get(index - 2)) && str.get(index).equals(str.get(index - 1))) {
                str.remove(index);
                checkList(str, index);
            }
            //判断AABB
            else if (index > 2) {
                //判断AA //判断BB
                if (str.get(index - 3).equals(str.get(index - 2)) && str.get(index).equals(str.get(index - 1))) {
                    str.remove(index);
                    checkList(str, index);
                }else{
                    checkList(str, index + 1);
                }
            } else {
                checkList(str, index + 1);
            }
        }
    }
}
