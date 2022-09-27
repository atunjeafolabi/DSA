package chapter_7.positional_list;

import chapter_6.queue.Queue;

public interface PositionalQueue<E> extends Queue<E> {
    void remove(Position<E> p);
}
