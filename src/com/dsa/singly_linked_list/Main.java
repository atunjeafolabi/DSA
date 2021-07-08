package com.dsa.singly_linked_list;

public class Main {
    public static void main(String[] args){
        SinglyLinkedList<Person> persons = new SinglyLinkedList<Person>();

        System.out.println("Is persons list Empty? " + persons.isEmpty());
        System.out.println("Size of persons list: " + persons.size());

        Person segun = new Person("Segun Agunbiade", 22);
        Person john = new Person("John Quincy", 19);
        Person sam = new Person("Sam Doe", 15);

        persons.addFirst(segun);
        persons.addFirst(john);
        persons.addLast(sam);
        System.out.println("Is persons list Empty? " + persons.isEmpty());
        System.out.println("Size of persons list: " + persons.size());

        System.out.println(persons.first());
        System.out.println(persons.last());

        persons.removeFirst();
        System.out.println("Size of persons list: " + persons.size());
    }
}
