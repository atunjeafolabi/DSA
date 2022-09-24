package chapter_7.exercises;

import chapter_6.deque.Deque;
import chapter_7.list.List;
import chapter_7.positional_list.IterableArrayList.ArrayList;

/**
 * R-7.3
 *
 * Give an implementation of the deque ADT using an array list for storage.
 */
public class R73<E> implements Deque<E> {

    List<E> list;

    public R73() {
        list = new ArrayList<>();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public E first() {
        return list.get(0);
    }

    @Override
    public E last() {
        return list.get(size() - 1);
    }

    @Override
    public void addFirst(E e) {
        list.add(0, e);
    }

    @Override
    public void addLast(E e) {
        list.add(size(), e);
    }

    @Override
    public E removeFirst() {
        return list.remove(0);
    }

    @Override
    public E removeLast() {
        return list.remove(size() - 1);
    }
}
