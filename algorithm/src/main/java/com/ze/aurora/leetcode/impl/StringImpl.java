package com.ze.aurora.leetcode.impl;

import com.ze.aurora.annotation.Algorithm;
import com.ze.aurora.leetcode.IString;

public class StringImpl implements IString {
    @Override
    public char firstUniqChar(String s) {
        int[] ch = new int[26];

        for (int i = 0; i < s.length(); i ++) {
            ch[s.charAt(i) - 'a'] ++;
        }

        for (int i = 0; i < s.length(); i ++) {
            if (ch[s.charAt(i) - 'a'] == 1) {
                return s.charAt(i);
            }
        }

        return ' ';
    }

    @Override
    public String reverseWords(String s) {
        s = s.trim();
        if (s.length() == 0) return "";
        StringBuffer result = new StringBuffer();

        int left = s.length() - 1;
        while (left >= 0) {
            while (left >= 0 && s.charAt(left) == ' ') left--;
            int right = left;

            while (left >= 0 && s.charAt(left) != ' ') left--;
            result.append(s, left+1, right+1);
            result.append(' ');
        }
        int len = result.length();
        return result.substring(0, len-1);
    }

    @Override
    @Algorithm(category = "字符串匹配", desc = "朴素算法：暴力")
    public String stringMatching(String text, String pattern) {
        for (int i = 0; i < text.length(); i ++) {
            boolean find = false;
            if (text.charAt(i) == pattern.charAt(0)) {
                for (int j = 1; j < pattern.length() && j+i < text.length(); j ++) {

                }
            }
        }
        return "";
    }


}
