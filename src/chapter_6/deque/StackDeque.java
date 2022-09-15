package chapter_6.deque;

import chapter_6.stack.LinkedStack;
import chapter_6.stack.Stack;

/**
 * C-6.31 Describe how to implement the deque ADT using two stacks as the only instance variables.
 * What are the running times of the methods?
 *
 * The idea here is to use two stacks cascaded bottom-to-bottom so that the their tops face opposite directions
 * (i.e the top of one stack faces the left and the top of the other faces the right)
 */
public class StackDeque<E> implements Deque<E> {

    Stack<E> stack1 = new LinkedStack<>();
    Stack<E> stack2 = new LinkedStack<>();

    @Override
    public int size() {
        return stack1.size() + stack2.size();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public E first() {
        return stack1.top();
    }

    @Override
    public E last() {
        return stack2.top();
    }

    @Override
    public void addFirst(E e) {
        stack1.push(e);
    }

    @Override
    public void addLast(E e) {
        stack2.push(e);
    }

    @Override
    public E removeFirst() {
        return stack1.pop();
    }

    @Override
    public E removeLast() {
        return stack2.pop();
    }
}
