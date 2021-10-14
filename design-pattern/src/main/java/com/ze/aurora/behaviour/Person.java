package com.ze.aurora.behaviour;

import lombok.SneakyThrows;

/**
 * 模板方法
 *
 * 人的一生从整体看来是一样的，经历出生、生活和死亡。
 * 但是每个人生活的方式不同，随具体的人而变化。
 * @author Aurora
 * @date 2021/10/8 19:40
 */
public abstract class Person {
    public final void exec() {
        born();
        live();
        die();
    }

    protected void born() {
        System.out.println("born");
    }


    protected abstract void live();

    protected void die() {
        System.out.println("die");
    }

    class NormalPerson extends Person {
        @Override
        protected void live() {
            System.out.println("normal live");
        }
    }


}

