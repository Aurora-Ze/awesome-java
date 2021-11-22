package com.ze.aurora.behaviour;

import java.util.ArrayList;
import java.util.List;

/**
 * 访问者模式
 *
 * 对原有的对象组合进行扩展
 * @author Aurora
 * @date 2021/11/17 22:06
 */
public class Visitor {

    public interface IVisitor {
        void visitMenu(MenuItem element);
        void visitMaterial(Material element);
    }

    class ConcreteVisitor implements IVisitor {

        @Override
        public void visitMenu(MenuItem element) {
            // visitor add ...
            element.getState();
            // visitor add ...
        }

        @Override
        public void visitMaterial(Material element) {
            element.getState();
        }
    }

    abstract class Element {
        String name;

        Element(String name) {
            this.name = name;
        }

        abstract void accept(IVisitor visitor);
        abstract void getState();

    }

    class MenuItem extends Element {

        MenuItem(String name) {
            super(name);
        }

        @Override
        void accept(IVisitor visitor) {
            visitor.visitMenu(this);
        }

        @Override
        void getState() {
            System.out.println(name + "的营养价值为10");
        }
    }

    class Material extends Element {

        Material(String name) {
            super(name);
        }

        @Override
        void accept(IVisitor visitor) {
            visitor.visitMaterial(this);
        }

        @Override
        void getState() {
            System.out.println(name + "的营养价值为5");
        }
    }

    class ObjectStructure {
        List<Element> lists;

        ObjectStructure() {
            this.lists = new ArrayList<>();
        }

        void addElement(Element element) {
            lists.add(element);
        }

        void recurse(IVisitor visitor) {
            for(Element e : lists) {
                e.accept(visitor);
            }
        }
    }

    public void test() {
        ObjectStructure obj = new ObjectStructure();
        obj.addElement(new MenuItem("宫爆鸡丁"));
        obj.addElement(new Material("鸡肉"));

        obj.recurse(new ConcreteVisitor());
    }

    public static void main(String[] args) {
        Visitor visitor = new Visitor();
        visitor.test();
    }
}
