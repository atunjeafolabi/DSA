package com.dsa;

import java.util.Arrays;

/**
 * Chapter 3
 * ---------
 * In the element uniqueness problem, we are given an array with n elements and asked whether all elements of that
 * collection are distinct from each other. Our ﬁrst solution to this problem uses a straightforward iterative algorithm.
 * The unique1 method, given in Code Fragment 4.7, solves the element uniqueness problem by looping through all distinct pairs of
 * indices j < k, checking if any of those pairs refer to elements that are equivalent to each other. It does this using two nested for loops,
 * such that the ﬁrst iteration of the outer loop causes n−1 iterations of the inner loop, the second iteration of the outer loop causes n − 2
 * iterations of the inner loop, and so on. Thus, the worst-case running time of this method is proportional to (n−1)+(n−2)+···+2+1,
 * which we recognize as the familiar O(n^2) summation from Proposition 4.3
 * This method is implemented in unique1 and has a time complexity of O(n^2).
 *
 *
 * An even better algorithm for the element uniqueness problem is based on using sorting as a problem-solving tool.
 * In this case, by sorting the array of elements, we are guaranteed that any duplicate elements will be placed next to each other.
 * Thus, to determine if there are any duplicates, all we need to do is perform a single pass over the sorted array, looking for
 * consecutive duplicates.
 * This method is implemented in unique2 and has a time complexity of O(nlogn).
 * (TODO: I still need to figure out how this time complexity for unique2 was arrived at)
 */
public class ElementUniqueness {
    public static void main(String args[]){
        int[] array1 = {2, 7, 3, 5, 1, 4, 6};       // with unique elements
//        int[] array1 = {2, 7, 3, 5, 1, 4, 6, 1};    // with elements not unique

        System.out.println(unique1(array1));
//        System.out.println(unique2(array1));
    }

    /** Returns true if there are no duplicate elements in the array. */
    public static boolean unique1(int[ ] data) {
        int n = data.length;
        for (int j=0; j < n-1; j++)
            for (int k=j+1; k < n; k++)
                if (data[j] == data[k])
                    return false; // found duplicate pair
        return true; // if we reach this, elements are unique
    }

//    /** Returns true if there are no duplicate elements in the array. */
//    public static boolean unique2(int[ ] data) {
//        int n = data.length; int[ ] temp = Arrays.copyOf(data, n); // make copy of data
//         Arrays.sort(temp); // and sort the copy
//         for (int j=0; j < n-1; j++)
//             if (temp[j] == temp[j+1]) // check neighboring entries
//                 return false; // found duplicate pair
//         return true; // if we reach this, elements are unique
//    }
}
