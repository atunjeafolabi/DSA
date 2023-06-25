package chapter_9.exercises;

import chapter_6.queue.Queue;
import chapter_9.heap.HeapPriorityQueue;
import chapter_9.priority_queue.PriorityQueue;
import chapter_9.priority_queue.UnsortedPriorityQueue;


/**
 * C-9.26
 *
 * Show how to implement the FIFO queue ADT using only a priority
 * queue and one additional integer instance variable.
*/
public class C926<E> implements Queue<E> {
    private PriorityQueue<Integer, E> queue = new UnsortedPriorityQueue();
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
    public void enqueue(E e) {
        queue.insert(x++, e);
    }

    @Override
    public E first() {
        return (E) queue.min().getValue();
    }

    @Override
    public E dequeue() {
        return (E) queue.removeMin().getValue();
    }

    public static void main(String[] args) {

        Queue queue = new C926<>();

        queue.enqueue("RED");
        queue.enqueue("ORANGE");
        queue.enqueue("YELLOW");
        queue.enqueue("GREEN");
        queue.enqueue("BLUE");


        System.out.println(queue.first());
        System.out.println(queue.dequeue());
        System.out.println(queue.first());


    }
}