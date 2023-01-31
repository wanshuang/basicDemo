package com.mianshi.demo;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

import java.util.Arrays;
import java.util.List;

public class GOMEDemo {
    public static void main(String[] args) {
        String s = "helllo woooooooooooooood";
        char seat = '$';
        char[] chars = s.toCharArray();
        for (int x = 0; x < s.toCharArray().length; x++) {
            if (x > 1) {
                //判断AAAAAA
                checkRep(chars, x, x - 1, seat);
                //判断AABB
                if (x > 2)
                    if (chars[x - 3] == chars[x - 2] && chars[x] == chars[x - 1]) {
                        chars[x] = seat;
                    }
            }
        }
        StringBuilder sb = new StringBuilder().append(chars);
        rmSeat(sb, String.valueOf(seat));
        System.out.println(sb.toString());
    }

    public static boolean checkRep(char[] chars, int index, int foot, char seat) {
        if (chars[foot] == seat) {
            checkRep(chars, index, foot - 1, seat);
        } else {
            if (chars[index] == chars[foot] && chars[index] == chars[foot - 1]) {
                chars[index] = seat;
            }
        }
        return false;
    }

    public static void rmSeat(StringBuilder sb, String seat) {
        if (sb.indexOf(seat) > -1) {
            sb.deleteCharAt(sb.indexOf(seat));
            rmSeat(sb, seat);
        }
    }

    @Test
    public void test() {
        String s = "ddz sdfasdf dfdf sdf";
        System.out.println(spStr2(s));
    }

    public int spStr(String str) {
        String[] strArray = str.split(" ");
        int length = 0;
        for (int x = 0; x < strArray.length; x++) {
            if (StringUtils.isNotBlank(strArray[x]) && strArray[x].length() > length) {
                length = strArray[x].length();
            }
        }
        return length;
    }

    public int spStr2(String str) {
        List<String> s = Arrays.asList(str.split(" "));
        s.sort((o1, o2) -> {
            if (o1.length() < o2.length())
                return 1;
            else if (o1.length() > o2.length())
                return -1;
            else return 0;
        });
        return s.get(0).length();
    }


}
