package chapter_4.exercises;

import java.util.Arrays;

/*
    Describe an efficient algorithm for finding the ten largest elements in an array of
    size n. What is the running time of your algorithm?
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

        for (int a : tenLargestElements) {
            System.out.println(a);
        }
    }
}
