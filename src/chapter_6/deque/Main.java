package chapter_6.deque;

public class Main {
    public static void main(String[] args) {
        Deque deque1 = new ArrayDeque<>(4);
        deque1.addFirst("Kunle");
        deque1.addFirst("Adeyanju");
        deque1.addFirst("Babatunde");
        deque1.addFirst("Tomori");

        System.out.println(deque1.size());
        System.out.println(deque1.removeLast());
        System.out.println(deque1.last());
        System.out.println(deque1.size());

        deque1.addLast("Askari");

        System.out.println(deque1.first());
    }
}
