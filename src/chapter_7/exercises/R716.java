package chapter_7.exercises;

import chapter_7.positional_list.IterableLinkedPositionalList.LinkedPositionalList;

import java.util.Iterator;

public class R716 {
    public static void main(String[] args) {

        // Iterator
        LinkedPositionalList<String> names = new LinkedPositionalList<>();
        names.addFirst("Konrad");
        names.addFirst("Johann");
        names.addFirst("Nikola");
        names.addFirst("Pachelbel");
        names.addFirst("Mozart");
        names.addFirst("Zuse");
        names.addFirst("Oliver");

        for (Iterator<String> it = names.alternateIterator(); it.hasNext(); ) {
            String name = it.next();
            System.out.println(name);
        }
    }
}
