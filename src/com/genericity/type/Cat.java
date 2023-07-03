package com.genericity.type;

import com.genericity.Animal;
import com.genericity.action.Run;

public class Cat extends Animal implements Run {

    public Cat(String name, String age) {
        super(name, age);
    }

    @Override
    public void doing() {
        System.out.println(String.format("我是:%s,今年%s岁",getName(),getAge()));
        running();
    }

    @Override
    public void running() {
        System.out.println("我会跑!");
    }
}
