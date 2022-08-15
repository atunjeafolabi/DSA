package chapter_3.linked_list.singly_linked_list;

import com.dsa.Person;

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

        System.out.println("First Person: " + persons.first());
        System.out.println("Last Person" + persons.last());
        System.out.println("Second to Last Person: " + persons.secondToLast());

//        persons.removeFirst();
//        System.out.println("Size of persons list: " + persons.size());

        System.out.println("\n---Equivalence Testing---\n");

        // Equivalence testing of two lists
        SinglyLinkedList<Person> otherPersons = new SinglyLinkedList<Person>();

        otherPersons.addFirst(segun);
        otherPersons.addFirst(john);
        otherPersons.addLast(sam);
        System.out.println("Should return true for two lists with thesame size and elements: " + persons.equals(otherPersons));

        // Add one more person to make the otherPersons list different from persons list
        Person ana = new Person("Ana", 25);
        otherPersons.addFirst(ana);
        System.out.println("Should return false for two lists with different size and elements:  " + persons.equals(otherPersons));

        // A List should equal itself
        System.out.println("A list should equal itself: " + persons.equals(persons));
    }
}
