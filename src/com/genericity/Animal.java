package com.genericity;

import jdk.nashorn.internal.objects.annotations.Constructor;

import java.util.Objects;

public abstract class Animal extends Organism {


    public Animal(String name, String age) {
        super(name, age);
    }

    public abstract void doing();

    public void toing(){
        doing();
    }

}
