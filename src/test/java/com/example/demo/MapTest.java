package com.example.demo;

import com.google.common.collect.Maps;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * @author ws
 * @date 2020/8/19
 */
public class MapTest {

    @Test
    public void testHashMap(){
        HashMap map = Maps.newHashMap();
        for(int i = 0; i < 18 ; i ++){
            map.put("k"+i, i);
        }

        map.keySet().forEach(k->{
            System.out.println(k +"," + map.get(k));
        });
    }

    @Test
    public void testLinkedHashMap(){
        LinkedHashMap map = new LinkedHashMap(16,0.75f,true);
        for(int i = 0; i < 18 ; i ++){
            map.put("k"+i, i);
        }


        map.entrySet().forEach(e->{
            System.out.println(e);
        });

        map.get("k3");
        System.out.println("------------------------------");


        map.entrySet().forEach(e->{
            System.out.println(e);
        });
    }

}
