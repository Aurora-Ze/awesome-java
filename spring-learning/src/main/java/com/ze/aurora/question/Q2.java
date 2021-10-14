package com.ze.aurora.question;

import java.util.Scanner;

/**
 * @author Aurora
 * @date 2021/9/28 18:55
 */
public class Q2 {
    static int[][] direct = new int[][]{
            {0, 1}, {0, -1}, {-1, 0}, {1, 0}
    };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        sc.close();

        Q2 q = new Q2();
        int[] local = q.getLocation(input);
        System.out.printf("(%d,%d)\n", local[0], local[1]);
    }

    public int[] getLocation(String input) {
        int[] base = new int[]{0, 0};
        int[] dir;
        for (int i = 0; i < input.length(); i ++) {
            dir = getDirect(input.charAt(i));
            base[0] += dir[0];
            base[1] += dir[1];
        }

        return base;
    }

    public int[] getDirect(char ch) {
        if (ch == 'U') {
            return direct[0];
        } else if (ch == 'D') {
            return direct[1];
        } else if (ch == 'L') {
            return direct[2];
        } else if (ch == 'R') {
            return direct[3];
        }
        return null;
    }
}
