package chapter_4.exercises;

import java.util.Arrays;

/*
    Describe an efficient algorithm for finding the ten largest elements in an array of
    size n. What is the running time of your algorithm?

    The Big-O of Arrays.sort() is O(nlogn) as defined in its implementation.
    The Big-O of the for-loop is O(1) because it always run in a constant number of times no matter the input. (i.e 10 times).
    Hence the overall Big-O = O(nlogn) + O(1) which is equal to O(nlogn).
*/
public class C436 {
    public static void main(String[] args) {
        int[] numbers = {10, 2, 5, 9, 2, 1, 3, 7, 11, 21, 16, 12, 18, 17, 19, 30, 25, 23};         // 18 numbers
        Arrays.sort(numbers);

        int j = 0;
        int lastIndex = numbers.length - 1;
        int[] tenLargestElements = new int[10];

        for (int i = lastIndex; i > lastIndex - 10 ; i--) {
            tenLargestElements[j] = numbers[i];
            j++;
        }

        printArray(tenLargestElements);
    }

    private static void printArray(int[] tenLargestElements) {
        for (int a : tenLargestElements) {
            System.out.println(a);
        }
    }
}
