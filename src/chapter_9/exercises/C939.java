package chapter_9.exercises;

import chapter_9.heap.MaxHeap;
import chapter_9.priority_queue.Entry;

import java.util.ArrayList;

/**
 * C-9.39
 *
 * Explain how the k largest elements from an unordered collection of size n
 * can be found in time O(n +klogn) using a maximum-oriented heap.
 *
 * (TODO: not sure about the time complexity)
 */
public class C939 {
    public static void main(String[] args) {
        // unordered key/value collection
        Integer[] keys = {3, 20, 30, 10, 40, 5};
        String[] values = {"U", "V", "W", "X", "Y", "Z"};

        MaxHeap<Integer, String> heap = new MaxHeap<>(keys, values);

        int k = 3;
        ArrayList<Entry<Integer, String>> maxKElements = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            maxKElements.add(heap.removeMax());
        }

        // Display the largest k elements
        for (Entry<Integer, String> entry : maxKElements) {
            System.out.println("[" + entry.getKey() + ", " + entry.getValue() + "]");
        }
    }
}
