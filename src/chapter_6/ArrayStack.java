package chapter_6;

/**
 * Code Fragment 6.2:
 * -----------------
 * Array-based implementation of the Stack interface.
 *
 * @param <E>
 */
public class ArrayStack<E> implements Stack<E> {

    private static final int CAPACITY = 1000;
    private int top = -1;
    private E[] data;

    public ArrayStack() {
        this(CAPACITY);
    }

    public ArrayStack(final int capacity) {
        // In java, type parameter E cannot be instantiated directly,
        // so we instantiate the generic Object class.
        // this.data = new E[capacity];  is not allowed
        // Instead, we use new Object[capacity] and then cast it to type E[]
        this.data = (E[]) new Object[capacity];     // safe cast; compiler may give warning
    }

    @Override
    public void push(E e) {
        if (data.length == size()) {
            throw new IllegalStateException("Stack is full");
        }

        data[++top] = e;
    }

    @Override
    public E pop() {
        if (this.isEmpty()) {
            return null;
        }

        E e = data[top];
        data[top] = null;
        top--;
        return e;
    }

    @Override
    public E top() {
        if (this.isEmpty()) {
            return null;
        }

        return data[top];
    }

    @Override
    public int size() {
        return this.top + 1;
    }

    @Override
    public boolean isEmpty() {
        return this.top == -1;
    }

    /**
     * Sample usage of the ArrayStack
     */
    public static class Main {
        public static void main(String[] args){
            // Using ArrayStack
    //        Stack<Integer> integerStack = new ArrayStack<>();
    //        integerStack.push(10);
    //        integerStack.push(15);
    //        integerStack.push(12);
    //
    //        System.out.println(integerStack.pop());
    //        System.out.println(integerStack.top());

            // Using Linked Stack
            Stack<Integer> integerStack = new LinkedStack<>();
            integerStack.push(10);
            integerStack.push(15);
            integerStack.push(12);

            System.out.println(integerStack.pop());
            System.out.println(integerStack.top());
        }
    }
}
