package chapter_7.exercises;

import chapter_7.positional_list.IterableLinkedPositionalList.LinkedPositionalList;

public class C744 {
    public static void main(String[] args) {
        LinkedPositionalList list = new LinkedPositionalList();
        LinkedPositionalList shuffledList;

        /*
         * even number of elements added (i.e 2n)
         * as specified in the question
         */
        list.addFirst("Ben");
        list.addLast("Sam");
        list.addFirst("Ken");
        list.addLast("John");
        list.addFirst("Adam");
        list.addLast("Eve");
        list.addFirst("Solomon");
        list.addFirst("Aaron");
        System.out.println(list.toString());

        shuffledList = list.cardShuffle(list);
        System.out.println(shuffledList.toString());
    }
}
