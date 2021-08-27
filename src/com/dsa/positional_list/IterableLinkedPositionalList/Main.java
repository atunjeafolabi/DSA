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

        for (Iterator<String> it = queueInSupermarket.iterator(); it.hasNext(); ) {
            String person = it.next();
            System.out.println(person);
        }
    }
}
