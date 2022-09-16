package chapter_6.stack;

/**
 * P-6.38
 *
 * The introduction of Section 6.1 notes that stacks are often used to provide “undo” support in applications like a
 * Web browser or text editor. While support for undo can be implemented with an unbounded stack, many applications
 * provide only limited support for such an undo history, with a ﬁxed-capacity stack. When push is invoked with the
 * stack at full capacity, rather than throwing an exception, a more typical semantic is to accept the pushed
 * element at the top while “leaking” the oldest element from the bottom of the stack to make room.
 * Give an implementation of such a LeakyStack abstraction, using a circular array.
 */
public class ArrayLeakyStack<E> implements Stack<E> {

    E[] data;
    int top = -1;
    int size;

    public ArrayLeakyStack() {
        data = (E[]) new Object[5];
    }

    @Override
    public void push(E e) {
        int avail = (top + 1) % data.length;
        data[avail] = e;
        top = avail;
        if (size() < data.length) {
            size++;
        }
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            return null;
        }
        E popped = data[top];
        data[top] = null;
        top = (top - 1 + data.length) % data.length;
        size--;

        return popped;
    }

    @Override
    public E top() {
        if (isEmpty()) {
            return null;
        }
        return data[top];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        int stackSize = size();
        str.append("[");
        for (int i = 0; i < stackSize; i++) {
            str = str.append(pop());
            if (i != stackSize - 1) {
                str = str.append(",");
            }
        }
        str.append("]");

        return str.toString();
    }
}
