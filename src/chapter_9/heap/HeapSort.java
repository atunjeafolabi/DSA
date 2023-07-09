package chapter_9.heap;

import chapter_9.priority_queue.DefaultComparator;

import java.util.ArrayList;

/**
 * P-9.50
 *
 * Section 9.4.2
 *
 * Implementing Heap-Sort in-place
 * (using max-oriented heap)
 *
 * --------------------------
 *
 * Problem C-9.46 can be solved using this same line of reasoning.
 * We will only just use the min-oriented heap approach instead.
 */
public class HeapSort<E> {

    private ArrayList<E> S;
    private DefaultComparator<E> comp;
    private int boundaryOfHeap = 0;

    public HeapSort(ArrayList<E> sequence) {
        S = sequence;
        comp = new DefaultComparator<>();

        // add each element of the sequence into a heap in-place
        for (int i = 0; i < S.size(); i++ ) {
            // expand heap boundary
            boundaryOfHeap = i;
            // add element to the heap by upheaping
            upheap(boundaryOfHeap);
        }
    }

    private int parent(int j) {
        return (j - 1) / 2;
    }

    private int left(int j) {
        return 2 * j + 1;
    }

    private int right(int j) {
        return 2 * j + 2;
    }

    private boolean hasLeft(int j) {
        return this.left(j) < boundaryOfHeap;
    }

    private boolean hasRight(int j) {
        return this.right(j) < boundaryOfHeap;
    }

    // max-oriented
    private void upheap(int j) {
        if (j > 0 && comp.compare(S.get(parent(j)), S.get(j)) < 0) {
            swap(j, parent(j));
            upheap(parent(j));
        }
    }

    private void swap(int i, int j) {
        E temp = S.get(i);
        S.set(i, S.get(j));
        S.set(j, temp);
    }

    public void sort() {
        for (int i = S.size() - 1; i >= 0; i--) {
            S.set(i, removeMax());
        }
    }

    /**
     * Remove max element from the heap portion of the sequence
     *
     * The element is not actually deleted, we are only moving
     * the boundary of the heap to exclude the max element
     */
    private E removeMax() {
        E max = S.get(0);
        swap(0, boundaryOfHeap);
        // S.remove(max);
        boundaryOfHeap--;
        downheap(0);

        return max;
    }

    // max-oriented
    private void downheap(int j) {

        if (hasLeft(j)) {
            int maxChildIndex = left(j);

            if (hasRight(j)) {
                if (comp.compare(S.get(left(j)), S.get(right(j))) < 0) {
                    maxChildIndex = right(j);
                }
            }
            if (comp.compare(S.get(j), S.get(maxChildIndex)) < 0) {
                swap(j, maxChildIndex);
                downheap(maxChildIndex);
            }
        }
    }

    /**
     * Print the heap generated in the constructor
     */
    public String display() {
        StringBuilder str = new StringBuilder();
        str.append("[");

        for (E entry : S) {
            str.append(entry);
            if (S.indexOf(entry) != S.size() - 1) {
                str.append(", ");
            }
        }

        str.append("]");

        return str.toString();
    }

    public static void main(String[] args) {

/*
-----------------------
            50
           /  \
         35   45
        / \   / \
       30 15 25 40
-----------------------
*/
        ArrayList<Integer> unsortedSequence = new ArrayList<>();
        unsortedSequence.add(40);
        unsortedSequence.add(30);
        unsortedSequence.add(25);
        unsortedSequence.add(35);
        unsortedSequence.add(15);
        unsortedSequence.add(45);
        unsortedSequence.add(50);

        HeapSort<Integer> hs = new HeapSort<>(unsortedSequence);

        System.out.println("Heap: " + hs.display());
        hs.sort();
        System.out.println("Sorted Sequence: " + hs.display());
    }
}
