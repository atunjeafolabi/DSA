package com.dsa;

/**
 *  Code Fragment 5.16:
 *  -------------------
 *  Reversing the elements of a sequence using iteration.
 */
public class ArrayReverseIterative {

    public static void main(String[] args){
        int[] ages = {4, 3, 6, 2, 8, 9, 3, 2};

        System.out.println("Original array:");
        dumpArray(ages);

        reverseIterative(ages);
    }

    /** Reverses the contents of the given array. */
    public static void reverseIterative(int[ ] data) {
        int low = 0, high = data.length - 1;
        while (low < high) {                // swap data[low] and data[high]
            int temp = data[low];
            data[low++] = data[high];       // post-increment of low
            data[high--] = temp;            // post-decrement of high
        }

        System.out.println("\nReversed array:");
        dumpArray(data);
    }

    // Helper class for dumping array contents
    public static void dumpArray(int[] array) {
        for (int     x : array){
            System.out.print(x + ",");
        }
    }
}
