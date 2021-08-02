package com.dsa.recusrion;

/**
 * Code Fragments 5.8 and 5.9
 * --------------------------
 * Recursive Algorithms for Computing Powers
 *
 * We wish to define the power function as power(x,n) = x^n
 * We will consider two different recursive formulations for the problem that lead to algorithms with very different performance.
 * A trivial recursive definition follows from the fact that x^n = x·(x^(n−1)) for n > 0.
 * We consider only non-negative indices n here.
 */
public class Power {

    public static void main(String[] args){
//        System.out.println(power1(3,4));
        System.out.println(power2(3,4));
//        System.out.println(power1(3,-1));         // negative indices throws a StackOverflowError. We should cater for this condition
    }

    /*
     * Computing the power function using trivial recursion
     * Time Complexity: O(n)
     */
    public static double power1(double x, int n) {
        if (n == 0)
            return 1;
        else
            return x * power1(x, n - 1);
    }

    /*
     * Computing the power function using repeated squaring.
     * e.g  2^13 = ((2^6)^2) * 2  , for 13 (odd index)
     * And  2^8  = ((2^4)^2)    ,   for 8 (even index)
     * This method is faster than that in power1
     * Time complexity: O(log n)
     */
    public static double power2(double x, int n) {

        if (n == 0)
            return 1;
        else {
            double partial = power2(x, n/2);             // rely on truncated division of n
            double result = partial * partial;
             if (n % 2 == 1)                               // if n odd, include extra factor of x
                  result *= x;
             return result;
        }

    }
}
