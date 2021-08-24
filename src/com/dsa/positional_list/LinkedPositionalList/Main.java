package com.dsa.positional_list.LinkedPositionalList;

import com.dsa.positional_list.Position;
import com.dsa.positional_list.PositionalList;

public class Main {
    public static void main(String[] args){
        PositionalList<String> queueInBank = new LinkedPositionalList<>();

        queueInBank.addLast("John");
        queueInBank.addLast("Ana");
        queueInBank.addLast("Segun");
        queueInBank.addLast("Bryan");

//        System.out.println(queueInBank.last().getElement());

        Position<String> firstPosition = queueInBank.first();
        String firstPerson = firstPosition.getElement();
        System.out.println(firstPerson);

        Position<String> secondPosition = queueInBank.after(firstPosition);
        String secondPerson = secondPosition.getElement();
        System.out.println(secondPerson);
    }
}
