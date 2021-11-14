package com.ze.aurora.behaviour;

/**
 * @author Aurora
 * @date 2021/11/14 21:44
 */
public class Iterator {
    interface Aggregate {
        MyIterator createIterator();
    }

    interface MyIterator<Item> {
        Item next();
        boolean hasNext();
    }

    class ConcreteAggregate implements Aggregate {
        private Integer[] items;

        /** 初始化数据 */
        public void initData() {
            items = new Integer[]{1,2,3,4,5,6};
        }

        @Override
        public MyIterator createIterator() {
            return new ConcreteIterator<>(items);
        }
    }

    class ConcreteIterator<Item> implements MyIterator {
        private Item[] items;
        private int position;

        ConcreteIterator (Item[] items) {
            this.items = items;
        }

        @Override
        public Object next() {
            return items[position++];
        }

        @Override
        public boolean hasNext() {
            return position < items.length;
        }
    }

    public void test () {
        ConcreteAggregate aggregate = new ConcreteAggregate();
        aggregate.initData();
        MyIterator iterator = aggregate.createIterator();
        while(iterator.hasNext()) {
            System.out.println(iterator.next());;
        }
    }
}
