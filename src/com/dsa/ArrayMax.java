package com.dsa;

/*
    The algorithm in arrayMax function runs in O(n) time
 */
public class ArrayMax {
    public static void main(String args[]){
        double[] array1 = {3.3, 9, 2.5, 5, 3, 1, 10};
        System.out.println(arrayMax(array1));
    }

    /** Returns the maximum value of a nonempty array of numbers. */
    private static double arrayMax(double[ ] data) {
        int n = data.length;
        double currentMax = data[0];    // assume first entry is biggest (for now)
        for (int j=1; j < n; j++)       // consider all other entries
            if (data[j] > currentMax)   // if data[j] is biggest thus far...
                currentMax = data[j];   // record it as the current max

        return currentMax;
    }
}
