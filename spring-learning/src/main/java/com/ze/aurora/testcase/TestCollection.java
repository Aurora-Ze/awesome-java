package com.ze.aurora.testcase;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Aurora
 * @date 2021/9/24 15:10
 */
public class TestCollection {
    public static void main(String[] args) {
        // set
        HashSet<Integer> set = new HashSet<>();
        boolean pass = set.add(null);
        System.out.println(pass);
        set.add(null);



        // table
        Hashtable<Integer, Integer> table = new Hashtable<>();
        table.put(1,1);
        table.put(1,2);

        // map
        HashMap<Integer, Integer> map = new HashMap<>();
        Integer num1 = map.put(1,1);
        Integer num2 = map.put(1,2);
        Integer num3 = map.put(null,null);
        Integer num4 = map.put(null,2);
        System.out.printf("numbers: %d, %d, %d, %d\n", num1, num2, num3, num4);

    }



}
