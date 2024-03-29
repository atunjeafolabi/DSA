package chapter_7.positional_list.FavoritesList;

/**
 * Code Fragment 7.16 and 7.17
 * ---------------------------
 * Favourites List
 * Maintains a list of elements ordered according to access frequency (i.e count).
 *
 * Favourites List maintains a collection of elements while keeping track of the number of times each element is accessed.
 * Keeping such access counts allows us to know which elements are among the most popular. Examples of such scenarios include
 * a Web browser that keeps track of a user’s most accessed pages, or a music collection that maintains a list of the most
 * frequently played songs for a user.
 */

import chapter_7.positional_list.IterableLinkedPositionalList.LinkedPositionalList;
import chapter_7.positional_list.Position;
import chapter_7.positional_list.PositionalList;
import org.w3c.dom.Node;

import java.util.Iterator;

public class FavoritesList<E> {

    protected static class Item<E> {
        private E value;
        private int count = 0;
        /*
         * Constructs new item with initial count of zero.
         */
        public Item(E val) {
            value = val;
        }
        public int getCount() {
            return count;
        }
        public E getValue() {
            return value;
        }
        public void increment() {
            count++;
        }
    }

    PositionalList<Item<E>> list = new LinkedPositionalList<>();                            // list of Items
    public FavoritesList() { }                                                              // constructs initially empty favorites list

    // nonpublic utilities
    /*
     * Provides shorthand notation to retrieve user's element stored at Position p.
     */
    protected E value(Position<Item<E>> p) {
        return p.getElement().getValue();
    }

    /*
     * Provides shorthand notation to retrieve count of item stored at Position p.
     */
    protected int count(Position<Item<E>> p) {
        return p.getElement().getCount();
    }

    /*
     * Returns Position having element equal to e (or null if not found).
     */
    protected Position<Item<E>> findPosition(E e) {
        Position<Item<E>> walk = list.first();
        while (walk != null && !e.equals(value(walk))) {
            walk = list.after(walk);
        }
        return walk;
    }

    /*
     * Moves item at Position p earlier in the list based on access count.
     */
    protected void moveUp(Position<Item<E>> p) {
        int cnt = count(p);                                                                         // revised count of accessed item
         Position<Item<E>> walk = p;
         while (walk != list.first() && count(list.before(walk)) < cnt) {
             walk = list.before(walk);                                                              // found smaller count ahead of item
         }
         if (walk != p) {
             list.addBefore(walk, list.remove(p));                                                  // remove/reinsert item
         }
    }

    // public methods
    /*
     * Returns the number of items in the favorites list.
     */
    public int size() {
        return list.size();
    }

    /*
     * Returns true if the favorites list is empty.
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /*
     * Accesses element e (possibly new), increasing its access count.
     */
    public void access(E e) {
        Position<Item<E>> p = findPosition(e);                                                  // try to locate existing element
        if (p == null) {
            p = list.addLast(new Item<E>(e));                                                   // if new, place at end
        }
        p.getElement().increment();                                                             // always increment count
        moveUp(p);                                                                              // consider moving forward
    }

    /*
     * Removes element equal to e from the list of favorites (if found)
     */
    public void remove(E e) {
        Position<Item<E>> p = findPosition(e);                                                  // try to locate existing element
        if (p != null) {
            list.remove(p);
        }
    }

    /*
     * Returns an iterable collection of the k most frequently accessed elements.
     */
    public Iterable<E> getFavorites(int k) throws IllegalArgumentException {
        if (k < 0 || k > size()) {
            throw new IllegalArgumentException("Invalid k");
        }
        PositionalList<E> result = new LinkedPositionalList<>();
        Iterator<Item<E>> iter = list.iterator();
        for (int j=0; j < k; j++) {
            result.addLast(iter.next().getValue());
        }
        return result;
    }

    /**
     * R-7.24
     *
     * Implement a resetCounts() method for the FavoritesList class that resets all elements’
     * access counts to zero (while leaving the order of the list unchanged).
     */
    public void resetCounts() {
        Position<Item<E>> walk = list.first();
        for (int i = 0; i < size(); i++) {
            walk.getElement().count = 0;
            walk = list.after(walk);
        }
    }
}