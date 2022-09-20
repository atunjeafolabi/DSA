package chapter_7.exercises;

import chapter_7.positional_list.IterableLinkedPositionalList.LinkedPositionalList;
import chapter_7.positional_list.PositionalList;

public class R712 {
    public static void main(String[] args) {
        PositionalList<String> states = new LinkedPositionalList<>();
        states.addFirst("Lagos");
        states.addFirst("Ekiti");
        states.addFirst("Oyo");
        states.addFirst("Ondo");
        states.addFirst("Kaduna");

        System.out.println("Index of first entry: " + states.indexOf(states.first()));
        System.out.println("Index of last entry: " + states.indexOf(states.last()));
    }
}
