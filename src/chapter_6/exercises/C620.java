package chapter_6.exercises;

import chapter_6.stack.LinkedStack;

/**
 * Suppose you have three nonempty stacks R, S, and T. Describe a sequence of operations that results in S storing all elements
 * originally in T below all of S’s original elements, with both sets of those elements in their original order. The final
 * configuration for R should be the same as its original configuration. For example, if R = (1,2,3), S = (4,5),
 * and T = (6,7,8,9), when ordered from bottom to top, then the final configuration should have
 * R = (1,2,3) and S = (6,7,8,9,4,5).
 *
 *
 * TIP: The idea here is to pop all the contents of S onto R, then also pop all the contents of T onto R.
 * Finally, pop the contents of R onto S and stop when the total number of S and T elements is reached.
 */
public class C620 {
    public static void main(String[] args) {
        LinkedStack<Integer> R = new LinkedStack<>();
        LinkedStack<Integer> S = new LinkedStack<>();
        LinkedStack<Integer> T = new LinkedStack<>();

        R.push(1);
        R.push(2);
        R.push(3);

        S.push(4);
        S.push(5);

        T.push(6);
        T.push(7);
        T.push(8);
        T.push(9);

        int sizeST = S.size() + T.size();

        while (S.size() > 0) {
            R.push(S.pop());
        }

        while (T.size() > 0) {
            R.push(T.pop());
        }

        for (int i = 0; i < sizeST; i++) {
            S.push(R.pop());
        }

        System.out.println(S.toString());
    }
}
