package chapter_6;

/**
 * A collection of objects that are inserted(pushed) and removed(popped) according to the LIFO principle.
 * Although similar in purpose, this interface differs from java.util.Stack; perhaps in its method naming.
 *
 * @param <E>
 */
public interface Stack<E> {

    /**
     * Adds element e to the top of the stack.
     *
     * @param e E
     */
    void push(E e);

    /**
     * Removes and returns the top element from the stack (or null if the stack is empty).
     *
     * @return E
     */
    E pop();

    /**
     * Returns the top element of the stack, without removing it (or null if the stack is empty).
     *
     * @return
     */
    E top();

    /**
     * Returns the number of elements in the stack.
     *
     * @return Size of stack
     */
    int size();

    /**
     * Returns a boolean indicating whether the stack is empty.
     *
     * @return {@code true} or {@code false}
     */
    boolean isEmpty();

}

