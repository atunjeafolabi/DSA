package com.dsa.stack;

import java.util.Arrays;

/**
 * Code Fragment 6.5 and 6.6:
 * --------------------------
 * A generic method that reverses the elements in an array with objects of type E, using
 * a stack declared with the interface Stack<E> as its type.
 */
public class ArrayReverse
{
    public static void main(String args[ ]) {
        Integer[] a = {4, 8, 15, 16, 23, 42};             // autoboxing allows this
        String[] s = {"Jack", "Kate", "Hurley", "Jin", "Michael"};
        System.out.println("a = " + Arrays.toString(a));
        System.out.println("s = " + Arrays.toString(s));
        System.out.println("Reversing...");
        reverse(a);
        reverse(s);
        System.out.println("a = " + Arrays.toString(a));
        System.out.println("s = " + Arrays.toString(s));
    }

    /** A generic method for reversing an array. */
    public static <E> void reverse(E[ ] a) {
        Stack<E> buﬀer = new ArrayStack<>(a.length);
        for (int i=0; i < a.length; i++)
            buﬀer.push(a[i]);
        for (int i=0; i < a.length; i++) a[i] = buﬀer.pop();
    }
}
