package chapter_6.exercises;

import chapter_6.deque.Deque;
import chapter_6.deque.StackDeque;

public class C631 {
    public static void main(String[] args) {
        Deque<Integer> numbers = new StackDeque<>();
        numbers.addFirst(10);
        numbers.addFirst(20);
        numbers.addLast(80);
        numbers.addLast(90);

        System.out.println(numbers.removeFirst());
        System.out.println(numbers.removeFirst());
        System.out.println(numbers.removeLast());
        System.out.println(numbers.removeLast());
    }
}
