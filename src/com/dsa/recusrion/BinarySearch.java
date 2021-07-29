package com.dsa.recusrion;

public class BinarySearch {

    public static void main(String[] args){

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
