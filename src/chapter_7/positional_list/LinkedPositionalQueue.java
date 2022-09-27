package chapter_7.positional_list;

import chapter_7.positional_list.IterableLinkedPositionalList.LinkedPositionalList;

/**
 * R-7.15
 *
 * To better model a FIFO queue in which entries may be deleted before reaching the front, design a LinkedPositionalQueue
 * class that supports the complete queue ADT, yet with enqueue returning a position instance and support for a new
 * method, remove(p), that removes the element associated with position p from the queue. You may use the adapter
 * design pattern (Section 6.1.3), using a LinkedPositionalList as your storage.
 *
 * Issue:
 * As stated in the question, the enqueue method of the LinkedPositionalQueue should return a Position instance
 * but the problem is that the signature of the enqueue method from Queue interface (has void return type)
 * cannot be changed in PositionalQueue interface (has Position<E> return type).
 * This prevents us from doing PositionalQueue<E> extends Queue<E>
 * ( i.e we couldn't take advantage of inheritance and therefore unable to re-use code! )
 *
 * Possible solution:
 * The contents of the Queue interface can be copied into the PositionalQueue interface!
 */
public class LinkedPositionalQueue<E> implements PositionalQueue<E> {

    PositionalList<E> list = new LinkedPositionalList<>();

    @Override
    public void enqueue(E e) {
        list.addLast(e);
    }

    @Override
    public void remove(Position<E> p) {
        list.remove(p);
    }

    @Override
    public E dequeue() {
        return list.remove(list.first());
    }

    @Override
    public E first() {
        return list.first().getElement();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }
}
