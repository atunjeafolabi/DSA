package chapter_7.exercises;

import chapter_7.positional_list.IterableLinkedPositionalList.LinkedPositionalList;

public class C736 {
    public static void main(String[] args) {
        LinkedPositionalList<Integer> list = new LinkedPositionalList<>();
        list.addFirst(10);
        list.addFirst(15);
        list.addFirst(20);
        list.addFirst(25);
        list.addFirst(30);

        System.out.println(list.positionAtIndex(0).getElement());        // should output 30

        // C-7.37
        System.out.println(list.positionAtIndex2(1).getElement());        // should output 25
    }
}
