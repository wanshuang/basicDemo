package com.example.demo;

import com.google.common.collect.Maps;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @author ws
 * @date 2020/8/20
 */
public class LFU {

    class LFUTool<K,V>{

        int size = 20;

        HashMap<K,V> valMap = Maps.newHashMap();

        HashMap<K, HitRate> hitRateMap = Maps.newHashMap();

        public LFUTool() {
        }

        public LFUTool(int size) {
            this.size = size;
        }

        class HitRate implements Comparable<HitRate>{

            public HitRate(K key) {
                this.key = key;
                this.date = System.currentTimeMillis();
                this.hit = 1l;
            }

            K key;

            Long date;

            Long hit;

            @Override
            public int compareTo(HitRate h) {
                int compare = this.hit.compareTo(h.hit);
                return compare == 0 ? this.hit.compareTo(h.date) : compare;
            }

            public Long getDate() {
                return date;
            }

            public void setDate(Long date) {
                this.date = date;
            }

            public Long getHit() {
                return hit;
            }

            public void setHit(Long hit) {
                this.hit = hit;
            }

            @Override
            public String toString() {
                return "HitRate{" +
                        "key=" + key +
                        ", date=" + date +
                        ", hit=" + hit +
                        '}';
            }
        }



        public void put(K k,V v){
            if(valMap.size() > size){
                removeLastElement();
            }
            if(valMap.get(k) == null){
                valMap.put(k,v);
            }
            modifyHitRate(k);

        }

        /**
         * 维护LFU命中以及时间
         * @param k
         */
        public void modifyHitRate(K k){
            if(hitRateMap.get(k) != null){
                HitRate hr = hitRateMap.get(k);
                hr.setDate(System.currentTimeMillis());
                hr.setHit(hr.getHit() + 1);
            }else{
                hitRateMap.put(k,new HitRate(k));
            }
        }

        public void removeLastElement(){
            HitRate hr = Collections.min(hitRateMap.values());
            if(Objects.nonNull(hr)){
                hitRateMap.remove(hr.key);
                valMap.remove(hr.key);
            }
        }



        public V get(K k){
            if(valMap.get(k) != null){
                modifyHitRate(k);
            }
            return valMap.get(k);
        }

        public void print(){
            valMap.entrySet().forEach(e->System.out.println(e));
            hitRateMap.entrySet().forEach(e->System.out.println(e.toString()));
        }

    }

    @Test
    public void testLFU(){
        LFUTool<String, String> lfuTool = new LFUTool<>();
        Random random = new Random();
        for(int index = 0 ; index < 1000 ; index ++){
            String v = random.nextInt(20) + "";
            String k = "k" + v;
            lfuTool.put(k,v);
        }
        lfuTool.print();
    }

}
