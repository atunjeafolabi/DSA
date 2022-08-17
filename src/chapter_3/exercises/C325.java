package chapter_3.exercises;

import chapter_3.linked_list.singly_linked_list.SinglyLinkedList;

public class C325 {
    public static void main(String[] args) {
        SinglyLinkedList<String> names1 = new SinglyLinkedList<>();
        names1.addFirst("Agunbiade");
        names1.addFirst("Elebuibon");
        names1.addFirst("Awolola");

        SinglyLinkedList<String> names2 = new SinglyLinkedList<>();
        names2.addLast("Konrad");
        names2.addLast("Muller");

        SinglyLinkedList<String> newList = SinglyLinkedList.concatLists(names1, names2);

        System.out.println(newList);
    }
}
