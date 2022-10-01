package chapter_7.exercises;

import chapter_6.queue.Queue;
import chapter_6.stack.LinkedStack;
import chapter_6.stack.Stack;

/**
 * C-7.34
 *
 * Describe how to implement the queue ADT using two stacks as instance variables,
 * such that all queue operations execute in amortized O(1) time.
 * Give a formal proof of the amortized bound.
 */
public class StackQueue<E> implements Queue<E> {

    Stack<E> S1 = new LinkedStack<>();
    Stack<E> S2 = new LinkedStack<>();
    String lastOperation = "";

    @Override
    public void enqueue(E element) {
        if (lastOperation == "out") {
            while (!S2.isEmpty()) {
                S1.push(S2.pop());
            }
        }
        S1.push(element);
        lastOperation = "in";
    }

    @Override
    public E dequeue() {
        if (lastOperation == "in") {
            while (!S1.isEmpty()) {
                S2.push(S1.pop());
            }
        }
        lastOperation = "out";
        return S2.pop();
    }

    @Override
    public E first() {
        if (lastOperation == "in") {
            while (!S1.isEmpty()) {
                S2.push(S1.pop());
            }
        }
        lastOperation = "out";
        return S2.top();
    }

    @Override
    public int size() {
        if (lastOperation == "in") {
            return S1.size();
        }
        if (lastOperation == "out") {
            return S2.size();
        }
        return 0;
    }

    @Override
    public boolean isEmpty() {
        if (lastOperation == "in") {
            return S1.isEmpty();
        }
        if (lastOperation == "out") {
            return S2.isEmpty();
        }
        return true;
    }

    public static void main(String[] args) {
        StackQueue<String> Q = new StackQueue<>();
        System.out.println("Initial Queue size: " + Q.size());
        Q.enqueue("Faith");
        Q.enqueue("Olayinka");
        Q.enqueue("Mary");
        Q.enqueue("Funmi");
        System.out.println("Queue size after adding elements: " + Q.size());

        System.out.println("Dequeue one element: " + Q.dequeue());
        System.out.println("Queue size after dequeueing one element: " + Q.size());

        Q.enqueue("Ibukun");
        System.out.println("first: " + Q.first());
        System.out.println("Queue size after enqueueing another element: " + Q.size());

    }
}
