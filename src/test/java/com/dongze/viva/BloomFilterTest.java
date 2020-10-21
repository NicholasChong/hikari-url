package com.dongze.viva;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.util.*;

/**
 * @author Nicholas
 * @version 创建时间：2020/10/21
 * @ClassName BloomFilterTest
 */
public class BloomFilterTest {
    private static final int insertions = 1000000; //100w

    public static void main(String[] args) {
        BloomFilter<String> bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), insertions, 0.001);

        //初始化一个存储string数据的set，初始化大小100w
        Set<String> sets = new HashSet<>(insertions);
        //初始化一个存储string数据的set，初始化大小100w
        List<String> lists = new ArrayList<String>(insertions);

        //向三个容器初始化100万个随机并且唯一的字符串---初始化操作
        for (int i = 0; i < insertions; i++) {
            String uuid = UUID.randomUUID().toString();
            bloomFilter.put(uuid);
            sets.add(uuid);
            lists.add(uuid);
        }

        int wrong = 0;//布隆过滤器错误判断的次数
        int right = 0;//布隆过滤器正确判断的次数
        for (int i = 0; i < insertions*10; i++) {
            String test = i % 100 == 0 ? lists.get(i / 100) : UUID.randomUUID().toString();//按照一定比例选择bf中肯定存在的字符串
            if (bloomFilter.mightContain(test)) {
                if (sets.contains(test)) {
                    right++;
                } else {
                    wrong++;
                }
            }
        }

        System.out.println("=================right=====================" + right);//100
        System.out.println("=================wrong=====================" + wrong);
    }
}
