package chapter_9.exercises;

import chapter_9.heap.HeapPriorityQueue;
import chapter_9.priority_queue.Entry;

/**
 * C-9.34
 *
 * Given a heap H and a key k, give an algorithm to compute all the entries in H having a key
 * less than or equal to k. For example, given the heap of Figure 9.12a and query k = 7,
 * the algorithm should report the entries with keys 2, 4, 5, 6, and 7
 * (but not necessarily in this order). Your algorithm should run in
 * time proportional to the number of entries returned,
 * and should not modify the heap.
 */
public class C934 {
    public static void main(String[] args) {
        HeapPriorityQueue<Integer, String> H = new HeapPriorityQueue<>();
        H.insert(2, "B");
        H.insert(5, "A");
        H.insert(4, "C");
        H.insert(15, "K");
        H.insert(9, "F");
        Entry<Integer, String> entryToFind = H.insert(7, "Q");
        H.insert(6, "Z");
        H.insert(16, "X");
        H.insert(25, "J");
        H.insert(14, "E");
        H.insert(12, "H");
        H.insert(11, "S");
        H.insert(8, "W");
        H.insert(10, "L");

        for (Entry<Integer, String> entry: H.equalOrLessThan(entryToFind)) {
            System.out.println(entry.getKey());
        }
    }
}

