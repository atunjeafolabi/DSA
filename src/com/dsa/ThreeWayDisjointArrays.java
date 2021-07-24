package com.dsa;

/*
    Chapter 4: Code Fragment 4.5: Algorithm disjoint2 for testing three-way set disjointness.
    ----------------------------------------------------------------------------------------
    Suppose we are given three sets, A, B, and C, stored in three different integer arrays.
    We will assume that no individual set contains duplicate values, but that there may be some numbers
    that are in two or three of the sets. The three-way set disjointness problem is to determine if the intersection
    of the three sets is empty, namely, that there is no element x such that x ∈ A, x ∈ B, and x ∈ C
 */
public class ThreeWayDisjointArrays {
    public static void main(String args[]){
        int[] A = {2,8,10,6};
        int[] B = {22,21,29,20,25};
        int[] C = {31,39,32,30,35};

        System.out.println("Are the sets disjoint? " + disjoint2(A, B, C));
    }

    /** Returns true if there is no element common to all three arrays. */
    public static boolean disjoint2(int[ ] groupA, int[ ] groupB, int[ ] groupC) {
        for (int a : groupA) {
            for (int b : groupB) {
                if (a == b) {               // only check C when we ﬁnd match from A and B
                    for (int c : groupC) {
                        if (a == c)         // and thus b == c as well
                            return false;   // we found a common value
                    }
//                   break;   // A further improvement
                }
            }
        }
        return true;                        // if we reach this, sets are disjoint
    }
}
