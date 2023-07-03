package com.genericity.type;

import com.genericity.Animal;
import com.genericity.action.Fly;
import com.genericity.action.Run;

public class Mouse extends Animal implements Fly, Run {

    public Mouse(String name, String age) {
        super(name, age);
    }

    @Override
    public void doing() {
        System.out.println(String.format("我是:%s,今年%s岁",getName(),getAge()));
        running();
        flying();
    }

    @Override
    public void flying() {
        System.out.println("我会飞！");
    }

    @Override
    public void running() {
        System.out.println("我会跑!");
    }
}
