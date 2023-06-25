package chapter_9.exercises;

import chapter_6.stack.Stack;
import chapter_9.heap.HeapAdaptablePriorityQueue;

/**
 * C-9.25
 *
 * Show how to implement the stack ADT using only a priority
 * queue and one additional integer instance variable.
 */
public class C925<E> implements Stack<E> {
    private HeapAdaptablePriorityQueue queue = new HeapAdaptablePriorityQueue();
    private int x;

    @Override
    public int size() {
        return queue.size();
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public void push(E e) {
        /**
         * Incremental priority key is (-1 * x++)
         *
         * We multiplied by -1 to always make the the last inserted
         * item on the stack be of the highest priority.
         *
         * In this case, the lowest (i.e most negative) key
         * has the highest priority
         */
        queue.insert((-1 * x++), e);
    }

    @Override
    public E top() {
        if (queue.isEmpty()) {
            return null;
        }
        return (E) queue.min().getValue();
    }

    @Override
    public E pop() {
        if (queue.isEmpty()) {
            return null;
        }
        x--;
        return (E) queue.removeMin().getValue();
    }

    public static void main(String[] args) {

        Stack stack = new C925<>();


        stack.push("RED");
        stack.push("ORANGE");
        stack.push("YELLOW");
        stack.push("GREEN");
        stack.push("BLUE");


        System.out.println(stack.top());
        System.out.println(stack.pop());
        System.out.println(stack.top());
        System.out.println(stack.pop());
        System.out.println(stack.top());

    }
}