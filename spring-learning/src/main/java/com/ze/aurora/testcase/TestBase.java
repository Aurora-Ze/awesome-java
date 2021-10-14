package com.ze.aurora.testcase;

import java.lang.reflect.Field;

/**
 * 测试Java基础
 * @author Aurora
 * @date 2021/10/14 11:57
 */
public class TestBase {
    public static void main(String[] args) {
        TestBase base = new TestBase();
        base.testModifyString();
    }

    void testIntegerAndBuffer() {
        Integer a = 127;
        Integer b = 127;
        System.out.println(a == b);

        Integer c = 128;
        Integer d = 128;
        System.out.println(c == d);
    }

    void testModifyString() {
        String s = "doki";
        Field valueField;
        char[] ch = null;

        try {
            valueField = String.class.getField("value");
            ch = (char[]) valueField.get(s);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
//        valueField.setAccessible(true);

        ch[3] = '_';
        System.out.println(s);
    }
}
