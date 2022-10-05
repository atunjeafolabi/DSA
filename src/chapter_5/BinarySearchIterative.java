package chapter_5;

/**
 * Code Fragment 5.15:
 * ------------------
 * A non-recursive implementation of binary search.
 */
public class BinarySearchIterative {

    public static void main(String[] args){
        int[] arr = {2, 4, 5, 7, 10, 14, 20, 30};
        System.out.println(binarySearch(arr, 17));
    }

    /** Returns true if the target value is found in the data array. */
    public static boolean binarySearch(int[ ] data, int target) {

        int low = 0;
        int high = data.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (target == data[mid])            // found a match
                 return true;
            else if (target < data[mid])
                high = mid - 1;                 // only consider values left of mid
            else
                low = mid + 1;                  // only consider values right of mid
        }
         return false;                          // loop ended without success
    }
}
