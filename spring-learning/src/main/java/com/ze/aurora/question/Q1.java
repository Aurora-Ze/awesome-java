package com.ze.aurora.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author Aurora
 * @date 2021/9/27 18:39
 */
public class Q1 {
    static Map<Integer, Integer> map;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] inputs = sc.nextLine().split(" ");

        int n = Integer.parseInt(inputs[0]);
        int k = Integer.parseInt(inputs[1]);
        map = new HashMap<>();

        int result = recur(n, k);
        System.out.println(result);
    }

    public static int recur(int n, int k) {
        if (n < k + 2) {
            return 1;
        }
        if (map.containsKey(n)) {
            return map.get(n);
        }
        if (((n - k) & 1) == 1) {
            return 1;
        }

        int base = (n - k) >>> 1;
        int left = recur(base, k);
        int right = recur(base + k, k);
        map.put(n, left + right);

        return left + right;
    }
}
