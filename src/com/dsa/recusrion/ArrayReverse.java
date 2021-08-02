package com.dsa.recusrion;

/**
 * Code Fragment 5.7:
 * -----------------
 * Reversing the elements of an array using linear recursion.
 */
public class ArrayReverse {

    public static void main(String[] args){
        int[] ages = {4, 3, 6, 2, 8, 9, 3, 2};
        final int arraySize = ages.length;

        System.out.println("Original array:");
        dumpArray(ages);
        System.out.println("\nReversed array:");
        dumpArray(
                reverseArray(ages, 0, arraySize - 1)
        );
    }

    /** Reverses the contents of subarray data[low] through data[high] inclusive. */
    public static int[] reverseArray(int[ ] data, int low, int high) {
        if (low < high) {                                           // if at least two elements in subarray
             int temp = data[low];                                  // swap data[low] and data[high]
             data[low] = data[high];
             data[high] = temp;
             reverseArray(data, low + 1, high - 1);     // recur on the rest
        }

        return data;
    }

    // Helper class for dumping array contents
    public static void dumpArray(int[] array) {
        for (int     x : array){
            System.out.print(x + ",");
        }
    }
}
