package chapter_9.exercises;

import chapter_9.heap.HeapPriorityQueue;

/**
 * C-9.37
 *
 * Suppose two binary trees, T1 and T2, hold entries satisfying the heap-order property
 * (but not necessarily the complete binary tree property). Describe a method for combining
 * T1 and T2 into a binary tree T, whose nodes hold the union of the entries in T1 and
 * T2 and also satisfy the heap-order property. Your algorithm should run in time
 * O(h1 + h2) where h1 and h2 are the respective heights of T1 and T2
 *
 * ( Not sure if this implementation has time complexity of O(h1 + h2) )
 */

public class C937 {

    public static void main(String[] args) {

        HeapPriorityQueue<Integer, String> t1 = new HeapPriorityQueue();
        HeapPriorityQueue<Integer, String> t2 = new HeapPriorityQueue();

        t1.insert(4, "C");
        t1.insert(9, "A");
        t1.insert(6, "Z");
        t1.insert(15, "K");
        t1.insert(9, "F");

        t2.insert(5, "J");
        t2.insert(14, "E");
        t2.insert(12, "H");
        t2.insert(7, "Q");
        t2.insert(20, "B");
        t2.insert(11, "S");
        t2.insert(13, "W");

        HeapPriorityQueue<Integer, String> t3 = HeapPriorityQueue.combine(t1, t2);

        while (!t3.isEmpty()) {
            System.out.println(t3.removeMin().getKey());
        }
    }


}