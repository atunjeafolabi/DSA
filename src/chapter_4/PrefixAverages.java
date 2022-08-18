package chapter_4;

/**
 * Chapter 3: Prefix Averages
 * --------------------------
 * Namely, given a sequence x consisting of n numbers, we want to compute a sequence a such that aj is the average
 * of elements x0 ,...,xj , for j = 0,...,n−1, that is,
 * aj = ( ∑ xi (from i=0 to j) ) / j +1
 *
 * The running time of prefixAverage2 is O(n)
 */
public class PrefixAverages {


    public static void main(String[] args){
        double[] array1 = {2,5,4,6,1};
        System.out.println("Original Array");
        dumpArray(array1);
        System.out.println("\nGenerated Prefix Averages");
        dumpArray(prefixAverage2(array1));
    }

    /** Returns an array a such that, for all j, a[j] equals the average of x[0], ..., x[j]. */
    public static double[ ] prefixAverage2(double[ ] x) {
        int n = x.length;
        double[] a = new double[n];                     // ﬁlled with zeros by default
        double total = 0;                               // compute preﬁx sum as x[0] + x[1] + ...
        for (int j = 0; j < n; j++) {
            total += x[j];                              // update preﬁx sum to include x[j]
            a[j] = total / (j + 1);                     // compute average based on current sum
        }
        return a;
    }

    public static void dumpArray(double[] array) {
        for (double x : array){
            System.out.print(x + ",");
        }
    }
}
