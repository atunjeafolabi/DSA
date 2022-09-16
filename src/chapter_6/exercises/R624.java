package chapter_6.exercises;

import chapter_6.queue.ArrayQueue;
import chapter_6.queue.Queue;
import chapter_6.stack.ArrayStack;
import chapter_6.stack.Stack;

/**
 * Suppose you have a stack S containing n elements and a queue Q that is initially empty. Describe how you can use Q to scan S
 * to see if it contains a certain element x, with the additional constraint that your algorithm must return the elements
 * back to S in their original order. You may only use S, Q, and a constant number of other primitive variables.
 *
 *
 * TIP: A useful idea here is that, when all the elements of a stack S is popped and enqueued onto a queue Q,
 * and then again pushed/returned onto stack S, the contents of the S is reversed.
 *
 * The elements of S are reversed twice, so that S still preserves its original order.
 */
public class R624 {
    public static void main(String[] args) {
        Stack<Integer> S = new ArrayStack<>();
        Queue<Integer> Q = new ArrayQueue<>();
        boolean isPresent = false;
        int x = 8;
        // fill S from 0 to 9
        for(int i = 0; i < 10; i++) {
            S.push(i);
        }

        while (S.size() > 0) {
            int poppedNum = S.pop();
            Q.enqueue(poppedNum);
            if (poppedNum == x) {
                isPresent = true;
            }
        }

        while (Q.size() > 0) {
            S.push(Q.dequeue());
        }

        while (S.size() > 0) {
            Q.enqueue(S.pop());
        }

        while (Q.size() > 0) {
            S.push(Q.dequeue());
        }

        System.out.println(S.toString());
        System.out.println("Is x present in the stack? " + isPresent);
    }
}
