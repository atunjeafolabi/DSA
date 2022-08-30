package chapter_5.exercises;

/**
 * R-5.1 Describe a recursive algorithm for finding the maximum element in an array, A, of n elements.
 * What is your running time and space usage?
 *
 * Running time: O(n)
 */
public class ArrayMax {
    public static void main(String[] args) {
        int[] data = {5, 3, 10, -7, 15, 6, 2, 4};
        System.out.println("Maximum element in array: " + findMax(data, 0, 0));
    }

    private static int findMax(int[] data, int walk, int tempMax) {

        if (data[walk] > tempMax) {
            tempMax = data[walk];
        }

        if (walk == data.length - 1) {
            return tempMax;
        }

        return findMax(data, ++walk, tempMax);
    }
}
