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
        StringBuffer buffer = new StringBuffer();
        int left = 0;
        int right = 0;

        return "";
    }
}
