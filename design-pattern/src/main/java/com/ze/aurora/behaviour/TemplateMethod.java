package com.ze.aurora.behaviour;

/**
 * 模板方法
 *
 * 人的一生从整体看来是一样的，经历出生、生活和死亡。
 * 但是每个人生活的方式不同，随具体的人而变化。
 * @author Aurora
 * @date 2021/11/9 18:03
 */
public class TemplateMethod {
    abstract class Person {
        public final void exec() {
            born();
            live();
            die();
        }

        private void born() {
            System.out.println("born");
        }

        protected abstract void live();

        private void die() {
            System.out.println("die");
        }
    }

    class OnePerson extends Person {

        @Override
        protected void live() {
            System.out.println("normal live");
        }
    }

    public void test() {
        Person person = new OnePerson();
        person.exec();
    }
}
