package com.ze.aurora.strategy;

import com.ze.aurora.annotation.LeetCode;

public interface IString {

    @LeetCode(name = "第一个只出现一次的字符")
    char firstUniqChar(String s);

    @LeetCode(name = "翻转单词顺序", desc = "空格间隔，标点和字母视为相连")
    String reverseWords(String s);
}
