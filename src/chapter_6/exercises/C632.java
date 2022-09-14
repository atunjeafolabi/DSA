package chapter_6.exercises;

import chapter_6.deque.Deque;
import chapter_6.deque.LinkedDeque;
import chapter_6.stack.LinkedStack;
import chapter_6.stack.Stack;

/**
 * C-6.32
 * Suppose you have two nonempty stacks S and T and a deque D. Describe how to use D so that S stores all the elements
 * of T below all of its original elements, with both sets of elements still in their original order.
 */
public class C632 {
    public static void main(String[] args) {
        Stack<Integer> S = new LinkedStack<>();
        Stack<Integer> T = new LinkedStack<>();
        Deque<Integer> D = new LinkedDeque<>();

        // populate S with numbers 1 to 5
        for (int i = 4; i > 0; i--) {
            S.push(i);
        }

        // populate T with numbers 5 to 8
        for (int i = 8; i >= 5; i--) {
            T.push(i);
        }

        int sizeS = S.size();
        int sizeT = T.size();

        while (S.size() > 0) {
            D.addFirst(S.pop());
        }

        while (T.size() > 0) {
            D.addLast(T.pop());
        }

        for (int i = 1; i <= sizeT; i++ ) {
            S.push(D.removeLast());
        }

        for (int i = 1; i <= sizeS; i++ ) {
            S.push(D.removeFirst());
        }

        System.out.println(S.toString());
    }
}
