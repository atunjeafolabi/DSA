package com.dsa.positional_list.IterableLinkedPositionalList;

import com.dsa.positional_list.Position;

import java.util.Iterator;

public class Main {
    public static void main(String[] args){
        LinkedPositionalList<String> queueInSupermarket = new LinkedPositionalList<>();

        queueInSupermarket.addLast("Mike");
        queueInSupermarket.addLast("Adebola");
        queueInSupermarket.addLast("Bayo");
        queueInSupermarket.addLast("Ben");

//        for (Iterator<String> it = queueInSupermarket.iterator(); it.hasNext(); ) {
//            String person = it.next();
//            System.out.println(person);
//        }

        // Iterating through the contents/elements of the list directly
        for (String person : queueInSupermarket ) {
            System.out.println(person);
        }

        System.out.println("\n------------------\n");

        // Iterating though positions then subsequently getting the list contents
        for (Position<String> p : queueInSupermarket.positions()) {
            System.out.println(p.getElement());
        }
    }
}
