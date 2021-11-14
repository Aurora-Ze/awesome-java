package com.ze.aurora.behaviour;

/**
 * 策略
 *
 * Context可以决定使用算法1还是算法2，而不必了解算法的具体实现。
 * @author Aurora
 * @date 2021/11/9 19:22
 */
public class Strategy {
    interface IStrategy {
        void handle();
    }

    class Strategy1 implements IStrategy {

        @Override
        public void handle() {
            System.out.println("handle 1");
        }
    }

    class Strategy2 implements IStrategy {

        @Override
        public void handle() {
            System.out.println("handle 2");
        }
    }

    class Context {
        IStrategy strategy;

        Context(IStrategy strategy) {
            this.strategy = strategy;
        }

        void exec() {
            strategy.handle();
        }

        void setStrategy(IStrategy strategy) {
            this.strategy = strategy;
        }
    }

    public void test() {
        Context context = new Context(new Strategy1());
        context.exec();

        context.setStrategy(new Strategy2());
        context.exec();
    }
}
