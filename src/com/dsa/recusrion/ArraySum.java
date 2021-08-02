package com.dsa.recusrion;

/**
 * Code Fragment 5.6:
 * ------------------
 * Summing an array of integers using linear recursion.
 */
public class ArraySum {

    public static void main(String[] args){
        int[] ages = {4, 3, 6, 2, 8, 9, 3, 2};
        final int arraySize = ages.length;

        System.out.println("Sum of array elements: " + linearSum(ages, arraySize));
    }

    public static int linearSum(int[ ] data, int n) {
        if (n == 0)
            return 0;
        else
            return linearSum(data, n - 1) + data[n - 1];
    }
}
