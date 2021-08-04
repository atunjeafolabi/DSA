package com.dsa.recusrion;

public class Fibonacci {

    public static void main(String[] args){
//        System.out.println(fibonacciBad(4));

        System.out.println(
                "F(n): " + fibonacciGood(4)[0] + "\n" +
                "F(n-1): " + fibonacciGood(4)[1]
        );
    }

    /**
     * Returns the nth Fibonacci number (inefficiently).
     * Using Binary recursion
     * Time complexity: Exponential
     * */
    public static long fibonacciBad(int n) {
        if (n <= 1)
            return n;
        else
            return fibonacciBad(n-2) + fibonacciBad(n-1);
    }

    /**
     * Returns array containing the pair of Fibonacci numbers, F(n) and F(n−1).
     * Using Linear recursion
     * Time complexity: Linear time, O(n)
     *
     * This method saves the result of already computed steps and avoids repetitive computation
     * */
    public static long[ ] fibonacciGood(int n) {
        if (n <= 1) {
            long[ ] answer = {n, 0};
            return answer;
        } else {
            long[ ] temp = fibonacciGood(n - 1);              // returns {F n−1 , F n−2 }
            long[ ] answer = {temp[0] + temp[1], temp[0]};      // we want {F n , F n−1 }
            return answer;
        }
    }
}
