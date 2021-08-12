package com.ze.aurora.strategy;

import com.ze.aurora.annotation.LeetCode;

public interface IString {

    @LeetCode(name = "第一个只出现一次的字符")
    char firstUniqChar(String s);
}
