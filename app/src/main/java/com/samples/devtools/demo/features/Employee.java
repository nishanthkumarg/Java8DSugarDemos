package com.samples.devtools.demo.features;

/**
 * Created by pprabhakaran on 4/13/17.
 */

public class Employee {

    public enum Gender  { MALE, FEMALE }

    private final String name;
    private final int age;
    private final Gender gender;

    public Employee(String name, int age, Gender gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }
}
