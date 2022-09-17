package chapter_3.linked_list.circularly_linked_list;

import chapter_3.Person;

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


//        CircularlyLinkedList list1 = new CircularlyLinkedList();
//        CircularlyLinkedList list2 = new CircularlyLinkedList();
//
//        list1.addFirst("a");
//        list1.addFirst("b");
//        list1.addFirst("c");
//
//
//        list2.addFirst("b");
//        list2.addFirst("c");
//        list2.addFirst("a");
//
//        System.out.println("List 1: " + list1.toString());
//        System.out.println("List 2: " + list2.toString());
//
//        if (CircularlyLinkedList.isSameSequenceOfElements(list1,list2)) {
//            System.out.println("The two lists are of thesame sequence");
//        } else {
//            System.out.println("The two lists are not thesame sequence");
//        }

        // Clone
        CircularlyLinkedList<Integer> ages = new CircularlyLinkedList<>();
        ages.addFirst(10);
        ages.addLast(20);
        ages.addLast(30);
        ages.addLast(40);
        ages.addLast(50);
        ages.addLast(60);

        try {
            System.out.println(ages.clone());
        } catch (CloneNotSupportedException e) {
            System.out.println("clone not supported");
        }
    }
}
