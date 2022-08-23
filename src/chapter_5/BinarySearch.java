package chapter_5;

/**
 * Code Fragment 5.3:
 * -----------------
 * An implementation of the binary search algorithm on a sorted array using recursion.
 * Return the index of the target if found.
 * Return -1 if the target is not found
 * Time Complexity: O(logn)
 *
 * This kind of recursion is an example of tail recursion
 */
public class BinarySearch {

    public static void main(String[] args){
        int[] ages = {2, 4, 5, 7, 10, 14, 20, 30};
        System.out.println(binarySearch(145, ages, 0, 7));
    }

    public static int binarySearch(int target, int[] array, int indexLowest, int indexHighest) {
        if (indexLowest > indexHighest) {
            return -1;
        }

        int middle = (indexHighest + indexLowest) / 2;
        int number = array[middle];

        if (target == number) {
            return middle;
        }

        if (target < number) {
            return binarySearch(target, array, indexLowest, middle - 1);
        } else {
            return binarySearch(target, array, middle + 1, indexHighest);
        }
    }
}
