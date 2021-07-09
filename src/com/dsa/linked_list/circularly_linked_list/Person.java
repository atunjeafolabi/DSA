package com.dsa.linked_list.circularly_linked_list;

public class Person {
    String name;
    int age;

    public Person(String name, int age){
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\nAge: " + age;
    }
}
