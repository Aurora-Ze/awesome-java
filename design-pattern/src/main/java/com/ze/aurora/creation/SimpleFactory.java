package com.ze.aurora.creation;

/**
 * 简单工厂
 *
 * 在简单工厂类中根据用户的条件返回对应的产品，从而将创建产品的逻辑解耦开。
 * @author Aurora
 * @date 2021/11/9 20:09
 */
public class SimpleFactory {

    // 枚举类型，表示牛奶或茶
    enum ProductType {
        PRODUCT_TYPE_MILK,
        PRODUCT_TYPE_TEA;
    }

    interface Product{}

    class Milk implements Product{}

    class Tea implements Product{}

    class SimpleFactoryDemo {

        Product createProduct(ProductType type) {
            Product product = null;
            switch (type) {
                case PRODUCT_TYPE_MILK:
                    product = new Milk();
                    break;
                case PRODUCT_TYPE_TEA:
                    product = new Tea();
                    break;
            }
            return product;
        }
    }

    public void test() {
        SimpleFactory.SimpleFactoryDemo demo = new SimpleFactoryDemo();
        Product product = demo.createProduct(ProductType.PRODUCT_TYPE_MILK);
    }


}
