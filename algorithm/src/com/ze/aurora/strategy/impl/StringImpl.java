package com.ze.aurora.strategy.impl;

import com.ze.aurora.strategy.IString;

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

    public static void main(String[] args) {
        StringImpl impl = new StringImpl();
        String s = impl.reverseWords(" the sky is blue.");
        System.out.printf("\"%s\"", s);
    }
}
