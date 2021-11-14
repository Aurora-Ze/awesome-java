package com.ze.aurora.creation;

/**
 * 工厂方法
 *
 * 具体的创建方法延迟到子类去实现，然后通过不同的工厂来获得不同产品。
 * @author Aurora
 * @date 2021/11/9 20:21
 */
public class FactoryMethod {
    interface Product{}

    class Milk implements Product {}

    class Tea implements Product {}


    /** 具体方法交给子类延迟实现*/
    abstract class Factory {
        protected abstract Product createProduct();
    }

    class MilkFactory extends Factory {
        @Override
        protected Product createProduct() {
            return new Milk();
        }
    }

    class TeaFactory extends Factory {
        @Override
        protected Product createProduct() {
            return new Tea();
        }
    }

    public void test() {
        Factory factory = new TeaFactory();
        Product product = factory.createProduct();
    }
}
