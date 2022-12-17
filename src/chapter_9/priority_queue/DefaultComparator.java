package chapter_9.priority_queue;

import java.util.Comparator;

/**
 * Code Fragment 9.4:
 *
 * A DefaultComparator class that implements a comparator
 * based upon the natural ordering of its element type.
 */
public class DefaultComparator<E> implements Comparator<E> {
    @Override
    public int compare(E a, E b) {
        return ((Comparable<E>) a).compareTo(b);
    }
}