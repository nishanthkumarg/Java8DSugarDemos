package com.samples.devtools.demo.features;

import java.util.Optional;

/**
 * Created by nishanthkumarg on 3/29/17.
 */

public class Person {

    Person(String name, int age, int id){
        this.name = name;
        this.age = age;
        this.id = id;
    }
    private String name;
    private int age;
    public int id;

    Person(){

    }

    public int getId() {
        return id;
    }

    public interface testInterface {
        int getId(Person p);
    }
}
