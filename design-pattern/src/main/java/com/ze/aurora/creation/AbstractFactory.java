package com.ze.aurora.creation;

/**
 * 抽象工厂
 *
 * 一个工厂提供多个方法来创建组成产品的不同部分，因此它实际创建了一系列产品。
 * 工厂中创建的不同对象是有联系的。
 * @author Aurora
 * @date 2021/11/11 13:56
 */
public class AbstractFactory {
    abstract class CPU {}
    class IntelCPU extends CPU {}
    class AMDCPU extends CPU {}

    abstract class Memory {}
    class SamsungMemory extends Memory {}
    class TOSHIBAMemory extends Memory {}

    interface ComputerFactory {
        CPU createCPU();
        Memory createMemory();
    }

    class ConcreteFactory1 implements ComputerFactory {

        @Override
        public CPU createCPU() {
            return new IntelCPU();
        }

        @Override
        public Memory createMemory() {
            return new SamsungMemory();
        }
    }

    class ConcreteFactory2 implements ComputerFactory {

        @Override
        public CPU createCPU() {
            return new AMDCPU();
        }

        @Override
        public Memory createMemory() {
            return new TOSHIBAMemory();
        }
    }

    public void test() {
        ComputerFactory factory = new ConcreteFactory1();
        // cpu和memory作为一个产品整体，不可随意搭配
        factory.createCPU();
        factory.createMemory();
    }
}
