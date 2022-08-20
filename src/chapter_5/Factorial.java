package chapter_5;

/**
 * Code Fragment 5.1
 * -----------------
 * A recursive implementation of the factorial function.
 * I think there is a better and more efficient solution to this
 */
public class Factorial {
    public static void main(String[] args){
        System.out.println(factorial(5));
    }

    public static int factorial(int n) throws IllegalArgumentException {
        if (n < 0)
            throw new IllegalArgumentException();       // argument must be non-negative
        else if (n == 0)
            return 1;                                   // base case
        else
            return n * factorial(n - 1);            // recursive case
    }
}
