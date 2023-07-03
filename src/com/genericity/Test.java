package com.genericity;

import com.genericity.type.Bird;
import com.genericity.type.Cat;
import com.genericity.type.Dog;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        Bird bird1 = new Bird("百灵鸟","5");
        Bird bird2 = new Bird("杜鹃","2");
        List<Bird> birds = new ArrayList<>();
        birds.add(bird1);
        birds.add(bird2);

        Cat cat1 = new Cat("暹罗猫","1");
        Cat cat2 = new Cat("布偶猫","4");
        List<Cat> cats = new ArrayList<>();
        cats.add(cat1);
        cats.add(cat2);

        Dog dog1 = new Dog("拉布拉多","8");
        Dog dog2 = new Dog("哈士奇","7");
        List<Dog> dogs = new ArrayList<>();
        dogs.add(dog1);
        dogs.add(dog2);

        List<Animal> animals = new ArrayList<>();
        addAnimail(animals,birds);
        addAnimail(animals,cats);
        addAnimail(animals,dogs);

        List<Organism> organisms = new ArrayList<>();
        addOrganism(organisms,birds);
        addOrganism(organisms,cats);
        addOrganism(organisms,dogs);

    }

    //所有动物
    private static void addAnimail(List<Animal> animals, List<? extends Animal> animals2){
        for (Animal a :animals2){
            animals.add(a);
            a.toing();
        }
    }

    //所有生物
    private static <K extends Animal,E extends Organism> void addOrganism(List<Organism> organisms, List<? extends Organism> organisms2){
        for (Organism a :organisms2){
            organisms.add(a);
        }
    }

//    //会奔跑的动物
//    private static void add(List<Animal> animals, List<? extends Animal & Run> animals2){
//        for (Animal a :animals2){
//            animals.add(a);
//        }
//    }
}
