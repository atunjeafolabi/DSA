package chapter_3.linked_list.doubly_linked_list;

import chapter_3.Person;

public class Main {
    public static void main(String[] args){
        DoublyLinkedList<Person> persons = new DoublyLinkedList<>();

        System.out.println("Is persons list Empty? " + persons.isEmpty());
        System.out.println("Size of persons list: " + persons.size());

        Person segun = new Person("Joe Doe", 22);
        Person john = new Person("Wasiu Alabi", 19);
        Person sam = new Person("Ben Adam", 15);

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
