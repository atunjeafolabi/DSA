package chapter_5.exercises;

/**
 * R-5.7
 * -----
 * Describe a recursive algorithm for computing the nth Harmonic number, defined
 * as Hn = Summation of 1/k from k = 1 to n.
 *
 * ex: 1 + 1/2 + 1/3 + 1/4 + ... + 1/n
 */
public class HarmonicNumber {

    public static void main(String[] args) {
        System.out.println("Nth Harmonic Number = " + calcNthHarmonicNumber(5));
    }

    public static double calcNthHarmonicNumber(int nth) {
        if (nth < 1) {
            throw new IllegalArgumentException("Parameter must not be less than 1");
        }

        if (nth == 1)
            return 1;

        return 1.0 / nth + calcNthHarmonicNumber(nth - 1);
    }
}
