package chapter_3.exercises;

import chapter_3.linked_list.doubly_linked_list.DoublyLinkedList;

public class C325DLL {
    public static void main(String[] args) {
        DoublyLinkedList<String> countries1 = new DoublyLinkedList<>();
        countries1.addFirst("Nigeria");
        countries1.addFirst("Ghana");
        countries1.addFirst("Togo");

        DoublyLinkedList<String> countries2 = new DoublyLinkedList<>();
        countries2.addLast("Germany");
        countries2.addLast("Austria");

        DoublyLinkedList<String> newList = DoublyLinkedList.concatLists(countries1, countries2);

        System.out.println(newList);
    }
}
