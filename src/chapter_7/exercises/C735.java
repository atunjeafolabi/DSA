package chapter_7.exercises;

import chapter_6.queue.Queue;
import chapter_7.list.List;

public class C735<E> implements Queue<E> {

    List<E> list = new C726<>(10);

    @Override
    public void enqueue(E element) {
        list.add(size(), element);
    }

    @Override
    public E dequeue() {
        return list.remove(0);
    }

    @Override
    public E first() {
        return list.get(0);
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
