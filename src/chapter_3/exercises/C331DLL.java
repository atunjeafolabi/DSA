package chapter_3.exercises;

import chapter_3.linked_list.doubly_linked_list.DoublyLinkedListTwo;

public class C331DLL {
    public static void main(String[] args) {
        DoublyLinkedListTwo<String> countries1 = new DoublyLinkedListTwo<>();
        countries1.addFirst("USA");
        countries1.addFirst("Canada");
        countries1.addFirst("Mexico");

        DoublyLinkedListTwo<String> countries2 = new DoublyLinkedListTwo<>();
        countries2.addLast("Brazil");
        countries2.addLast("Ecuador");

        DoublyLinkedListTwo<String> newList = DoublyLinkedListTwo.concatLists(countries1, countries2);

        System.out.println(newList);
    }
}
