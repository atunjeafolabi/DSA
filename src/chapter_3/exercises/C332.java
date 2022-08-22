package chapter_3.exercises;

import chapter_3.linked_list.doubly_linked_list.circular.CircularDoublyLinkedList;

public class C332 {
    public static void main(String[] args) {
        CircularDoublyLinkedList<String> names = new CircularDoublyLinkedList<>();

        System.out.println("Adding names ...");
        names.addFirst("Segun");
        names.addFirst("Olaide");
        names.addFirst("Eniola");
        names.addFirst("Ben");
        System.out.println("Names: " + names);

        System.out.println("Get First entry: " + names.first());
        System.out.println("Get Last entry: " + names.last());

        System.out.println("Removing first entry...");
        names.removeFirst();
        System.out.println(names);

        System.out.println("Removing last entry...");
        names.removeLast();
        System.out.println(names);

        System.out.println("Add last..");
        names.addLast("Mike");
        System.out.println(names);

        System.out.println("Rotate ..");
        names.rotate();
        System.out.println(names);

        System.out.println("Rotate again ..");
        names.rotate();
        System.out.println(names);

        System.out.println("Rotate backward ..");
        names.rotateBackward();
        System.out.println(names);

        System.out.println("Concatenate two lists ...");
        CircularDoublyLinkedList<String> colours1 = new CircularDoublyLinkedList<>();
        colours1.addFirst("Red");
        colours1.addFirst("Green");
        colours1.addFirst("Blue");

        CircularDoublyLinkedList<String> colours2 = new CircularDoublyLinkedList<>();
        colours2.addLast("Yellow");
        colours2.addLast("Purple");

        CircularDoublyLinkedList<String> newList = CircularDoublyLinkedList.concatLists(colours1, colours2);

        System.out.println(newList);
    }
}
