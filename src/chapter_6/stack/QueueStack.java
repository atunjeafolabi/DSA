package chapter_6.stack;

import chapter_6.queue.ArrayQueue;
import chapter_6.queue.Queue;

/*
 * C-6.25
 *
 * Describe how to implement the stack ADT using a single queue as an instance variable,
 * and only constant additional local memory within the method bodies. What is the running time
 * of the push(), pop(), and top() methods for your design?
 */
public class QueueStack<E> implements Stack<E> {

    private Queue<E> queue =  new ArrayQueue<>();

    @Override
    public void push(E e) {
        int size = size();
        queue.enqueue(e);
        for (int i = 0; i < size; i++) {
            queue.enqueue(queue.dequeue());
        }
    }

    @Override
    public E pop() {
        return queue.dequeue();
    }

    @Override
    public E top() {
        return queue.first();
    }

    @Override
    public int size() {
        return queue.size();
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public static void main(String[] args) {
        Stack<String> S = new QueueStack<>();
        S.push("Ajani");
        S.push("Babalola");
        S.push("Fadeke");
        System.out.println(S.pop());
        System.out.println(S.pop());
        System.out.println(S.pop());
    }
}
