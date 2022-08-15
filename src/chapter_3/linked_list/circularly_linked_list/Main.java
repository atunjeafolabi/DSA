package chapter_3.linked_list.circularly_linked_list;

import com.dsa.Person;

public class Main {
    public static void main(String[] args){
        CircularlyLinkedList<Person> persons = new CircularlyLinkedList<Person>();

        System.out.println("Is persons list Empty? " + persons.isEmpty());
        System.out.println("Size of persons list: " + persons.size());

        System.out.println("Adding some persons...");
        Person merry = new Person("Merry Ann", 22);
        Person ada = new Person("Ada Lovelace", 19);
        Person mike = new Person("Mike Faraday", 15);
        persons.addFirst(merry);
        persons.addFirst(ada);
        persons.addLast(mike);

        System.out.println("Is persons list Empty? " + persons.isEmpty());
        System.out.println("Size of persons list: " + persons.size());

        System.out.println(persons.first());
        System.out.println(persons.last());

        System.out.println("Removing first ...");
        persons.removeFirst();
        System.out.println("Size of persons list: " + persons.size());
    }
}
