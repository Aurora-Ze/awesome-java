package com.ze.aurora.leetcode;


import com.ze.aurora.annotation.Algorithm;
import com.ze.aurora.annotation.LeetCode;

public interface IString {

    @LeetCode(name = "第一个只出现一次的字符")
    char firstUniqChar(String s);

    @LeetCode(name = "翻转单词顺序", desc = "空格间隔，标点和字母视为相连")
    String reverseWords(String s);

    @Algorithm(category = "字符串匹配", desc = "待补充")
    String stringMatching(String text, String pattern);

}
