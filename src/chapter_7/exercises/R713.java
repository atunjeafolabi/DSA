package chapter_7.exercises;

import chapter_7.positional_list.IterableLinkedPositionalList.LinkedPositionalList;
import chapter_7.positional_list.PositionalList;

public class R713 {
    public static void main(String[] args) {
        PositionalList<String> colors = new LinkedPositionalList<>();
        colors.addFirst("red");
        colors.addFirst("green");
        colors.addFirst("blue");
        colors.addFirst("yellow");

        System.out.println("Is blue found in the list? " + colors.findPosition("blue"));                // position
        System.out.println("Is cyan found in the list? " + colors.findPosition("cyan"));                // null
    }
}
