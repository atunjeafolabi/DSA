package com.dsa.recusrion;

/**
 * Code Fragment 5.3:
 * -----------------
 * An implementation of the binary search algorithm on a sorted array using recursion.
 */
public class BinarySearch {

    public static void main(String[] args){
        int[] ages = {2, 4, 5, 7, 10, 14, 20, 30};
        System.out.println(search(14, ages, 0, 7));
    }

    public static int search(int target, int[] array, int indexLowest, int indexHighest) {
        if (indexLowest > indexHighest) {
            return -1;
        }

        int middle = (indexHighest + indexLowest) / 2;
        int number = array[middle];

        if (target == number) {
            return middle;
        }

        if (target < number) {
            return search(target, array, indexLowest, middle - 1);
        } else {
            return search(target, array, middle + 1, indexHighest);
        }
    }
}
