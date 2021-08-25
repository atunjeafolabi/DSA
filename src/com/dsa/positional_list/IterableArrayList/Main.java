package com.dsa.positional_list.IterableArrayList;

import com.dsa.Lists.List;

import java.util.Iterator;

public class Main {
    public static void main(String[] args){
        ArrayList<String> items = new ArrayList<>();
        items.add(0, "Pencil");
        items.add(1, "Biro");
        items.add(2, "Maths Set");
        items.add(3, "Notebook");

//        System.out.println(items.get(0));

        for (Iterator<String> it = items.iterator(); it.hasNext(); ) {
            String item = it.next();
            System.out.println(item);
        }

    }
}
