package chapter_5;

/**
 * Code Fragment 5.10:
 * ------------------
 * Summing the elements of a sequence using binary recursion.
 * Time complexity of binarySum is O(n), as there are 2n−1 method calls, each requiring constant time.
 */
public class BinarySum {

    public static void main(String[] args){
        int[] arr = {3,5,4,6,10,2};
        int low = 0;
        int high = arr.length - 1;
        System.out.println(binarySum(arr, low, high));
    }

    /** Returns the sum of subarray data[low] through data[high] inclusive. */
    public static int binarySum(int[ ] data, int low, int high) {
        if (low > high)                         // zero elements in subarray
             return 0;
        else if (low == high)                   // one element in subarray
            return data[low];
        else {
            int mid = (low + high) / 2;
            return binarySum(data, low, mid) + binarySum(data, mid+1, high);
        }
    }
}
